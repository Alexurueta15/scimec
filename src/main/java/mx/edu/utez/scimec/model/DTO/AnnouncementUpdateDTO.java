package mx.edu.utez.scimec.model.DTO;

import lombok.Getter;
import lombok.Setter;
import mx.edu.utez.scimec.model.Period;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AnnouncementUpdateDTO {
    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate finalDate;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime finalTime;

    @NotEmpty
    private String description;

    @NotEmpty
    private String image;
}
