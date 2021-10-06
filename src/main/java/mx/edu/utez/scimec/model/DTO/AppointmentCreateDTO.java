package mx.edu.utez.scimec.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentCreateDTO {

    @NotNull
    private LocalDateTime dateTime;

    @NotEmpty
    private String enrollment;

    @NotEmpty
    private String division;

    @NotEmpty
    private String career;

    @NotEmpty
    private String group;

    @NotEmpty
    private String quarter;

    @NotEmpty
    private String mentorFullName;

    @Email
    @NotNull
    private String mentorEmail;
}
