package mx.edu.utez.scimec.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class WorkerUpdateDTO {
    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private String secondLastname;

    @Email
    @NotNull
    private String email;

    @Email
    @NotNull
    private String institutionalEmail;

    @NotEmpty
    private String PID;

    @NotEmpty
    @Pattern(regexp = "^\\d+$", message = "Solo números")
    private String SSN;

    @NotEmpty
    @Pattern(regexp = "^\\d+$", message = "Solo números")
    private String phone;

    @NotEmpty
    private String position;
}
