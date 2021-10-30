package mx.edu.utez.scimec.controller;

import mx.edu.utez.scimec.Bean.SuccessMessage;
import mx.edu.utez.scimec.model.*;
import mx.edu.utez.scimec.model.DTO.*;
import mx.edu.utez.scimec.repository.*;
import mx.edu.utez.scimec.service.ExcelService;
import mx.edu.utez.scimec.util.DTO;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/admin/")
@RestController
public class AdminController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;
    private final PresentationRepository presentationRepository;
    private final PeriodRepository periodRepository;
    private final AnnouncementRepository announcementRepository;
    private final ExcelService excelService;
    private final AppointmentRepository appointmentRepository;

    public AdminController(BCryptPasswordEncoder bCryptPasswordEncoder, WorkerRepository workerRepository,
                           UserRepository userRepository, PresentationRepository presentationRepository,
                           PeriodRepository periodRepository, AnnouncementRepository announcementRepository,
                           ExcelService excelService, AppointmentRepository appointmentRepository) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.presentationRepository = presentationRepository;
        this.periodRepository = periodRepository;
        this.announcementRepository = announcementRepository;
        this.excelService = excelService;
        this.appointmentRepository = appointmentRepository;
    }

    //CRUD WORKER

    @PostMapping("worker")
    public SuccessMessage saveWorker(@DTO(WorkerCreateDTO.class) Worker worker) {
        worker.getUser().setPassword(bCryptPasswordEncoder.encode(worker.getUser().getPassword()));
        worker.setUser(userRepository.save(worker.getUser()));
        workerRepository.save(worker);
        return new SuccessMessage("Trabajador Registrado");
    }

    @PutMapping("worker")
    public SuccessMessage updateWorker(@DTO(WorkerUpdateDTO.class) Worker worker) {
        workerRepository.save(worker);
        return new SuccessMessage("Trabajador actualizado");
    }

    @DeleteMapping("worker")
    public SuccessMessage DeleteWorker(@DTO(WorkerDeleteDTO.class) Worker worker) {
        worker.getUser().setEnabled(!worker.getUser().getEnabled());
        userRepository.save(worker.getUser());
        return new SuccessMessage("Cambio de estado exitoso en Trabajador");
    }

    @GetMapping("worker")
    public List<Worker> findAllWorker() {
        return workerRepository.findAll();
    }

    //FIND ALL, CREATE AND DELETE IMAGE PRESENTATION

    @PostMapping("presentation")
    public SuccessMessage savePresentation(@DTO(PresentationCreateDTO.class) Presentation presentation) {
        presentationRepository.save(presentation);
        return new SuccessMessage("Imagen de presentación registrada");
    }

    @DeleteMapping("presentation")
    public SuccessMessage deletePresentation(@RequestBody @NotEmpty String id) {
        if (presentationRepository.existsById(id)) presentationRepository.deleteById(id);
        return new SuccessMessage("Imagen de presentación eliminada");
    }

    @GetMapping("presentation")
    public List<Presentation> findAllPresentation() {
        return presentationRepository.findAll();
    }

    //CRUD Period
    @PostMapping("period")
    public SuccessMessage savePeriod(@DTO(PeriodCreateDTO.class) Period period) {
        Period prevPeriodActive = periodRepository.findFirstByEnabledTrue();
        if (prevPeriodActive != null) {
            prevPeriodActive.setEnabled(false);
            periodRepository.save(prevPeriodActive);
        }
        periodRepository.save(period);
        return new SuccessMessage("Periodo registrado");
    }

    @PutMapping("period")
    public SuccessMessage updatePeriod(@DTO(PeriodUpdateDTO.class) Period period) {
        periodRepository.save(period);
        return new SuccessMessage("Periodo actualizado");
    }

    @GetMapping("period/active")
    public Period findPeriodActive() {
        return periodRepository.findFirstByEnabledTrue();
    }

    @GetMapping("period")
    public List<Period> findAll() {
        return periodRepository.findAll();
    }

    //Announcement
    @PostMapping("announcement")
    public SuccessMessage saveAnnouncement(@DTO(AnnouncementCreateDTO.class) Announcement announcement) {
        announcement.setPeriod(periodRepository.findFirstByEnabledTrue());
        announcementRepository.save(announcement);
        return new SuccessMessage("Convocatoria registrada");
    }

    @PutMapping("announcement")
    public SuccessMessage updateAnnouncement(@DTO(AnnouncementUpdateDTO.class) Announcement announcement) {
        announcementRepository.save(announcement);
        return new SuccessMessage("Convocatoria actualizada");
    }

    @GetMapping("announcement")
    public List<Announcement> findAllAnnouncement() {
        return announcementRepository.findAll();
    }

    @PostMapping("announcement/attendance")
    public ResponseEntity<byte[]> getAnnouncementListAttendance(
            @DTO(AnnouncementListAttendanceDTO.class) Announcement announcement) {
        byte[] contents = excelService.generateAnnouncementAttendanceFile(announcement);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", announcement.getName() + ".pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @GetMapping("appointment")
    public List<Appointment> findAllAppointment(@RequestParam(name = "startDate", defaultValue = "") String startDate,
                                                @RequestParam(name = "finalDate", defaultValue = "") String finalDate) {

        return appointmentRepository.findAllByDateTime(LocalDate.parse(startDate),LocalDate.parse(finalDate));
    }

    @PostMapping("appointment/file")
    public ResponseEntity<byte[]> getAppointmentList(@RequestParam(name = "startDate", defaultValue = "") String startDate,
                                                @RequestParam(name = "finalDate", defaultValue = "") String finalDate) {

        List<Appointment> appointmentList = appointmentRepository.findAllByDateTime(LocalDate.parse(startDate),LocalDate.parse(finalDate));
        String fileName = "Lista_citas_"+startDate+"_"+finalDate;

        byte[] contents = excelService.generateAppointmentResultFile(appointmentList);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentDispositionFormData("inline", fileName + ".xlsx");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }
}
