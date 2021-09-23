package mx.edu.utez.scimec.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserWorkerCreateDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @JsonIgnore
    private String role = "Worker";

    @JsonIgnore
    private boolean enabled = true;
}