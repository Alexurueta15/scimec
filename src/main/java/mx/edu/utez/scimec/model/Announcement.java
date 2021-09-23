package mx.edu.utez.scimec.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document
@Getter
@Setter
public class Announcement {
    @Id
    private String id;
    private String name;
    @DBRef
    private Period period;
    private LocalDate startDate;
    private LocalDate finalDate;
    private LocalTime startTime;
    private LocalTime finalTime;
    private String description;
    private String image;
}
