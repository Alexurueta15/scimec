package mx.edu.utez.scimec.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Getter
@Setter
public class Student {
    @Id
    private String id;
    @DBRef
    private User user;
    private String name;
    private String lastname;
    private String secondLastname;
    private String email;
    private String institutionalEmail;
    private String gender;
    private LocalDate birthDate;
    private String SSN;
    private Reference reference;
    private String disability;
    private String allergy;
    private String chronic;
    private String chronicProof;

    public String getFullname() {
        return name + " " + lastname + " " + secondLastname;
    }
}
