package mx.edu.utez.scimec.controller;

import mx.edu.utez.scimec.Bean.SuccessMessage;
import mx.edu.utez.scimec.model.DTO.WorkerCreateDTO;
import mx.edu.utez.scimec.model.DTO.WorkerDeleteDTO;
import mx.edu.utez.scimec.model.DTO.WorkerUpdateDTO;
import mx.edu.utez.scimec.model.Worker;
import mx.edu.utez.scimec.repository.UserRepository;
import mx.edu.utez.scimec.repository.WorkerRepository;
import mx.edu.utez.scimec.util.DTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/")
@RestController
public class AdminController {
    private final WorkerRepository workerRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminController(WorkerRepository workerRepository, UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("worker")
    public Object saveWorker(@DTO(WorkerCreateDTO.class) Worker worker) {
        worker.getUser().setPassword(bCryptPasswordEncoder.encode(worker.getUser().getPassword()));
        worker.setUser(userRepository.save(worker.getUser()));
        workerRepository.save(worker);
        return new SuccessMessage("Trabajador Registrado");
    }

    @PutMapping("worker")
    public Object updateWorker(@DTO(WorkerUpdateDTO.class) Worker worker) {
        workerRepository.save(worker);
        return new SuccessMessage("Trabajador actualizado");
    }

    @DeleteMapping("worker")
    public Object DeleteWorker(@DTO(WorkerDeleteDTO.class) Worker worker) {
        worker.getUser().setEnabled(!worker.getUser().getEnabled());
        userRepository.save(worker.getUser());
        return new SuccessMessage("Cambio de estado exitoso en Trabajador");
    }

    @GetMapping("worker")
    public List<Worker> findAllWorker() {
        return workerRepository.findAll();
    }
}
