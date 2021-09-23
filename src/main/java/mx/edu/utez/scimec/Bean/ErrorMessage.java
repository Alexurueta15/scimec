package mx.edu.utez.scimec.Bean;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
public class ErrorMessage {
    private final int statusCode = HttpStatus.BAD_REQUEST.value();
    private final Date timestamp = new Date();
    private final String message = "Operación fallida";
    private final String description;
    public ErrorMessage(String description) {
        this.description = description;
    }
}
