package mx.edu.utez.scimec.controller;

import mx.edu.utez.scimec.Bean.SuccessMessage;
import mx.edu.utez.scimec.model.DTO.StudentCreateDTO;
import mx.edu.utez.scimec.model.Student;
import mx.edu.utez.scimec.repository.StudentRepository;
import mx.edu.utez.scimec.repository.UserRepository;
import mx.edu.utez.scimec.util.DTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/public/")
@RestController
public class PublicController {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PublicController(UserRepository userRepository, StudentRepository studentRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("student")
    public SuccessMessage saveStudent(@DTO(StudentCreateDTO.class)Student student){
        student.getUser().setPassword(bCryptPasswordEncoder.encode(student.getUser().getPassword()));
        student.setUser(userRepository.save(student.getUser()));
        studentRepository.save(student);
        return new SuccessMessage("Estudiante registrado");
    }
}
