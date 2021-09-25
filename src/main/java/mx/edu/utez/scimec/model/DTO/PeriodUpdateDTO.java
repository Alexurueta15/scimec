package mx.edu.utez.scimec.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class PeriodUpdateDTO {

    @Id
    @NotEmpty
    private String id;

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    private LocalDate finalDate;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime finalTime;

    @NotEmpty
    @FutureOrPresent
    private List<LocalDate> holidays;
}
