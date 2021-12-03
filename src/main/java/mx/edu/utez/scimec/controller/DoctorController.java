package mx.edu.utez.scimec.controller;

import mx.edu.utez.scimec.Bean.ErrorMessage;
import mx.edu.utez.scimec.Bean.SuccessMessage;
import mx.edu.utez.scimec.model.Appointment;
import mx.edu.utez.scimec.model.DTO.AppointmentUpdateDTO;
import mx.edu.utez.scimec.model.DTO.PrescriptionAppointmentFileDTO;
import mx.edu.utez.scimec.model.User;
import mx.edu.utez.scimec.model.Worker;
import mx.edu.utez.scimec.repository.AppointmentRepository;
import mx.edu.utez.scimec.repository.UserRepository;
import mx.edu.utez.scimec.repository.WorkerRepository;
import mx.edu.utez.scimec.service.ExcelService;
import mx.edu.utez.scimec.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        return appointmentRepository.findAll();
    }

    @PutMapping("appointment")
    public Object setPrescriptionAppointment(@DTO(AppointmentUpdateDTO.class)Appointment appointment){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepository.findUserByUsername(user.getUsername());
        Worker worker = workerRepository.findByUser(user);
        String positionWorker = worker.getPosition().toUpperCase();
        if (positionWorker.contains("DOCTOR")){
            appointment.getPrescription().setAttendedBy(worker);
            appointmentRepository.save(appointment);
            return new SuccessMessage("Prescripción agregada");
        }else {
            return new ErrorMessage("Empleado no autorizado");
        }

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
