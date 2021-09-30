package mx.edu.utez.scimec.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
public class Appointment {
    @Id
    private String id;
    private LocalDateTime dateTime;
    @DBRef
    private Student student;
    private String enrollment;
    private String division;
    private String group;
    private String quarter;
    private String mentorFullName;
    private String mentorEmail;
    private Prescription prescription;
}
