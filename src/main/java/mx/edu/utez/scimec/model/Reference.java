package mx.edu.utez.scimec.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Reference {
    private String fullName;
    private String relationship;
    private String housePhone;
    private String mobilePhone;
}
