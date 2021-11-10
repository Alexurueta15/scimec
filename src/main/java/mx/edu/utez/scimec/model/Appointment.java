package mx.edu.utez.scimec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Appointment {
    @Id
    private String id;
    private LocalDateTime dateTime;
    @DBRef
    private Student student;
    private String enrollment;
    private String division;
    private String career;
    private String group;
    private String quarter;
    private String mentorFullName;
    private String mentorEmail;
    private Prescription prescription;

    public String getDateTimeFormat() {
        String meridianTime = dateTime.getHour() > 12 ? "pm" : "am";
        String hora = meridianTime.equals("pm") ? dateTime.getHour()-12 + ":" + dateTime.getMinute()
                + meridianTime : dateTime.getHour()  + ":" + dateTime.getMinute() + meridianTime;

        return dateTime.toLocalDate().toString() + " " + hora;
    }
}
