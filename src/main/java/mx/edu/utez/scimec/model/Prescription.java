package mx.edu.utez.scimec.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Prescription {

    private String height;
    private String bloodPressure;
    private String diagnostic;
    private String medicament;
    private String procedure;
    private String comment;
    @DBRef
    private Worker attendedBy;
}
