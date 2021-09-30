package mx.edu.utez.scimec.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PresentationCreateDTO {
    @NotEmpty
    private String image;
}
