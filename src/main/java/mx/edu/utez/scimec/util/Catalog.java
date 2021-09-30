package mx.edu.utez.scimec.util;

import mx.edu.utez.scimec.model.User;
import mx.edu.utez.scimec.repository.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.format.datetime.standard.TemporalAccessorParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        //obtención de casillas disponibles a partir de hora inicio y hora fin ingresando unidad de duración de citas
        /*
        * long durationUnit = 30;
        LocalTime startTime = LocalTime.parse("13:30");
        LocalTime finalTime = LocalTime.parse("16:00");
        List<LocalTime> lapses = new ArrayList<>();
        lapses.add(startTime);
        for (int i = 0; i < startTime.until(finalTime, ChronoUnit.MINUTES) / durationUnit; i++) {
            lapses.add(lapses.get(i).plus(durationUnit, ChronoUnit.MINUTES));
        }
        System.out.println("Lapsos disponibles: " + lapses);*/
    }
}
