package mx.edu.utez.scimec.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ReferenceStudentCreateDTO {
    @NotEmpty
    private String fullName;

    @NotEmpty
    private String relationship;

    @NotEmpty
    private String housePhone;

    @NotEmpty
    private String mobilePhone;
}
