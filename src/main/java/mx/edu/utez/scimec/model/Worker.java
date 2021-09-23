package mx.edu.utez.scimec.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Worker {
    @Id
    private String id;
    @DBRef
    private User user;
    private String name;
    private String lastname;
    private String secondLastname;
    private String email;
    private String institutionalEmail;
    private String PID;
    private String SSN;
    private String phone;
    private String position;
}
