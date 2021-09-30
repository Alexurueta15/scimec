package mx.edu.utez.scimec.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class StudentCreateDTO {
    @Valid
    @NotNull
    private UserStudentCreateDTO user;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastname;

    @NotNull
    private String secondLastname;

    @Email
    @NotNull
    private String email;

    @Email
    @NotNull
    private String institutionalEmail;

    @NotEmpty
    private String gender;

    @NotNull
    private LocalDate birthDate;

    @NotEmpty
    private String SSN;

    @Valid
    @NotNull
    private ReferenceStudentCreateDTO reference;

    @NotEmpty
    private String disability;

    @NotEmpty
    private String allergy;

    @NotEmpty
    private String chronic;

    private String chronicProof = "";
}
