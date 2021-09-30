package mx.edu.utez.scimec.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserStudentCreateDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @JsonIgnore
    private String role = "Student";

    @JsonIgnore
    private boolean enabled = true;
}