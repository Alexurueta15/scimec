package mx.edu.utez.scimec.controller;

import mx.edu.utez.scimec.Bean.SuccessMessage;
import mx.edu.utez.scimec.model.Announcement;
import mx.edu.utez.scimec.model.DTO.StudentCreateDTO;
import mx.edu.utez.scimec.model.Student;
import mx.edu.utez.scimec.repository.AnnouncementRepository;
import mx.edu.utez.scimec.repository.StudentRepository;
import mx.edu.utez.scimec.repository.UserRepository;
import mx.edu.utez.scimec.util.DTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/public/")
@RestController
public class PublicController {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AnnouncementRepository announcementRepository;

    public PublicController(UserRepository userRepository, StudentRepository studentRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder, AnnouncementRepository announcementRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.announcementRepository = announcementRepository;
    }


    @PostMapping("student")
    public SuccessMessage saveStudent(@DTO(StudentCreateDTO.class)Student student){
        student.getUser().setPassword(bCryptPasswordEncoder.encode(student.getUser().getPassword()));
        student.setUser(userRepository.save(student.getUser()));
        studentRepository.save(student);
        return new SuccessMessage("Estudiante registrado");
    }

    @GetMapping("announcement")
    public List<Announcement> findAllAnnouncement() {
        return announcementRepository.findAll();
    }
}
