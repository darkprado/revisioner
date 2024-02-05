package my.project.revisioner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author s.melekhin
 * @since 24 май 2023 г.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OperationNotFoundException extends RuntimeException {

    public OperationNotFoundException(String message) {
        super(message);
    }
}
