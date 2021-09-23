package mx.edu.utez.scimec.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Document
@Getter
@Setter
public class Period {
    @Id
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate finalDate;
    private LocalTime startTime;
    private LocalTime finalTime;
    private List<LocalDate> holidays;
    private Boolean enabled;
}
