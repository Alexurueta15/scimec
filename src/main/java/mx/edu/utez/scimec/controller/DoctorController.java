package mx.edu.utez.scimec.controller;

import mx.edu.utez.scimec.Bean.SuccessMessage;
import mx.edu.utez.scimec.model.Announcement;
import mx.edu.utez.scimec.model.Appointment;
import mx.edu.utez.scimec.model.DTO.AnnouncementListAttendanceDTO;
import mx.edu.utez.scimec.model.DTO.AppointmentUpdateDTO;
import mx.edu.utez.scimec.model.DTO.PrescriptionAppointmentFileDTO;
import mx.edu.utez.scimec.model.User;
import mx.edu.utez.scimec.repository.AppointmentRepository;
import mx.edu.utez.scimec.repository.UserRepository;
import mx.edu.utez.scimec.repository.WorkerRepository;
import mx.edu.utez.scimec.service.ExcelService;
import mx.edu.utez.scimec.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/doctor/")
@RestController
public class DoctorController {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final WorkerRepository workerRepository;
    private final ExcelService excelService;

    public DoctorController(AppointmentRepository appointmentRepository, UserRepository userRepository,
                            WorkerRepository workerRepository, ExcelService excelService){
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.workerRepository = workerRepository;
        this.excelService = excelService;
    }

    @GetMapping("appointment")
    public List<Appointment> findAllAppointment() {
        return appointmentRepository.findAllByDateTimeEquals(LocalDate.now());
    }

    @PutMapping("appointment")
    public SuccessMessage setPrescriptionAppointment(@DTO(AppointmentUpdateDTO.class)Appointment appointment){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepository.findUserByUsername(user.getUsername());
        appointment.getPrescription().setAttendedBy(workerRepository.findByUser(user));
        appointmentRepository.save(appointment);
        return new SuccessMessage("Prescripción agregada");
    }

    @PostMapping("appointment/prescription")
    public ResponseEntity<byte[]> getPrescriptionFile(
            @DTO(PrescriptionAppointmentFileDTO.class) Appointment appointment) {
        byte[] contents = excelService.generatePrescriptionFile(appointment);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", appointment.getStudent().getLastname() +
                appointment.getStudent().getSecondLastname() + appointment.getStudent().getName()+ ".pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }
}
