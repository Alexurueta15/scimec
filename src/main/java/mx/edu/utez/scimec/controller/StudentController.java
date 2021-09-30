package mx.edu.utez.scimec.controller;

import mx.edu.utez.scimec.Bean.SuccessMessage;
import mx.edu.utez.scimec.model.DTO.StudentUpdateDTO;
import mx.edu.utez.scimec.model.Student;
import mx.edu.utez.scimec.model.User;
import mx.edu.utez.scimec.repository.*;
import mx.edu.utez.scimec.util.DTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/student/")
@RestController
public class StudentController {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentController(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
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
}
