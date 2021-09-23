package mx.edu.utez.scimec.Bean;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
public class SuccessMessage {
    private final int statusCode = HttpStatus.OK.value();
    private final Date timestamp = new Date();
    private final String message = "Operación exitosa";
    private final String description;
    public SuccessMessage(String description) {
        this.description = description;
    }
}
