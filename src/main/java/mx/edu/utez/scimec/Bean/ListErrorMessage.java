package mx.edu.utez.scimec.Bean;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ListErrorMessage {
    private final int statusCode = HttpStatus.BAD_REQUEST.value();
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message = "Los argumentos no son válidos";
    private final List<String> errors;
    public ListErrorMessage(List<String> errors){this.errors = errors;}
}
