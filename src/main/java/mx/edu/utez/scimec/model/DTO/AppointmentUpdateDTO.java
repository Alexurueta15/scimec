package mx.edu.utez.scimec.model.DTO;

import lombok.Getter;
import lombok.Setter;
import mx.edu.utez.scimec.model.Prescription;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AppointmentUpdateDTO {

    @Id
    @NotEmpty
    private String id;
    @Valid
    @NotNull
    private PrescriptionAppointmentUpdateDTO prescription;
}
