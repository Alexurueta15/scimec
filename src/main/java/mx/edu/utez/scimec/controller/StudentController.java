package mx.edu.utez.scimec.controller;

import mx.edu.utez.scimec.Bean.SuccessMessage;
import mx.edu.utez.scimec.model.Appointment;
import mx.edu.utez.scimec.model.DTO.AppointmentCreateDTO;
import mx.edu.utez.scimec.model.DTO.AppointmentQueryDTO;
import mx.edu.utez.scimec.model.DTO.StudentUpdateDTO;
import mx.edu.utez.scimec.model.Period;
import mx.edu.utez.scimec.model.Student;
import mx.edu.utez.scimec.model.User;
import mx.edu.utez.scimec.repository.*;
import mx.edu.utez.scimec.util.DTO;
import mx.edu.utez.scimec.service.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequestMapping("/student/")
@RestController
public class StudentController {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final PeriodRepository periodRepository;
    private final EmailService emailService;

    public StudentController(StudentRepository studentRepository, UserRepository userRepository,
                             AppointmentRepository appointmentRepository, PeriodRepository periodRepository, EmailService emailService) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.periodRepository = periodRepository;
        this.emailService = emailService;
    }

    @GetMapping("profile")
    public Student getProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepository.findUserByUsername(user.getUsername());
        return studentRepository.findByUser(user);
    }

    @PutMapping("profile")
    public SuccessMessage updateStudent(@DTO(StudentUpdateDTO.class) Student student) {
        studentRepository.save(student);
        return new SuccessMessage("Estudiante actualizado");
    }

    @PostMapping("appointment")
    public SuccessMessage saveAppointment(@DTO(AppointmentCreateDTO.class) Appointment appointment) throws MessagingException {
        appointment.setStudent(getProfile());
        Period period = periodRepository.findFirstByEnabledTrue();
        appointment = appointmentRepository.save(appointment);
        period.getAppointments().add(appointment);
        periodRepository.save(period);
        emailService.confirmAppointment(getProfile().getInstitutionalEmail(), appointment);
        return new SuccessMessage("Cita registrada");
    }

    @GetMapping("appointment/available")
    public List<LocalTime> findAllAppointmentAvailableTime(@RequestParam(defaultValue = "") String date) {
        LocalDate localDate;
        if (date.isEmpty() || !date.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$"))
            localDate = LocalDate.now();
        else
            localDate = LocalDate.parse(date);
        Period period = periodRepository.findFirstByEnabledTrue();
        List<LocalTime> lapses = new ArrayList<>();
        if (period.getHolidays().contains(localDate)) return lapses;
        LocalTime startTime = period.getStartTime();
        LocalTime finalTime = period.getFinalTime();
        int durationUnit = 30;
        int nLapses = (int) (startTime.until(finalTime, ChronoUnit.MINUTES) / durationUnit);
        lapses.add(startTime);
        for (int i = 0; i < nLapses; i++) {
            lapses.add(lapses.get(i).plus(durationUnit, ChronoUnit.MINUTES));
        }
        List<Appointment> appointments = period.getAppointments();
        appointments.removeIf(appointment -> !appointment.getDateTime().toLocalDate().equals(localDate));
        for (Appointment appointment : appointments) {
            LocalTime time = appointment.getDateTime().toLocalTime();
            lapses.remove(time);
        }
        return lapses;
    }

    @GetMapping("appointment")
    public List<Appointment> findAllAppointment() {
        return appointmentRepository.findAllByStudent(getProfile());
    }

    @GetMapping("period/active")
    public Period findPeriodActive() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentRepository.findByUser(user);
        Period period = periodRepository.findFirstByEnabledTrue();
        period.getAppointments().removeIf(appointment -> !Objects.equals(appointment.getStudent().getId(), student.getId()));
        return period;
    }

    @GetMapping("period")
    public List<Period> findAll() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentRepository.findByUser(user);
        List<Period> periods = periodRepository.findAll();
        periods.forEach(period -> {
            period.getAppointments().removeIf(appointment -> !Objects.equals(appointment.getStudent().getId(), student.getId()));
        });
        return periods;
    }
}
