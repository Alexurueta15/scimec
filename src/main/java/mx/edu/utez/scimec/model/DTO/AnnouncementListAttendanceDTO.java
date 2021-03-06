package mx.edu.utez.scimec.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AnnouncementListAttendanceDTO {
    @Id
    @NotEmpty
    private String id;
}
