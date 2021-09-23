package mx.edu.utez.scimec.util;

import mx.edu.utez.scimec.model.User;
import mx.edu.utez.scimec.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Catalog implements CommandLineRunner {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Catalog(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args)  {
        User defaultUser = new User();
        defaultUser.setEnabled(true);
        defaultUser.setUsername("admin@localhost.com");
        if (!userRepository.existsByUsername(defaultUser.getUsername())){
            defaultUser.setPassword(bCryptPasswordEncoder.encode("Uno234"));
            defaultUser.setRole("Admin");
            userRepository.save(defaultUser);
        }
    }
}
