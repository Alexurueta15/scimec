package mx.edu.utez.scimec.model.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class PrescriptionAppointmentUpdateDTO {

    private String height;
    private String bloodPressure;
    private String diagnostic;
    private String medicament;
    private String procedure;
    private String comment;

}
