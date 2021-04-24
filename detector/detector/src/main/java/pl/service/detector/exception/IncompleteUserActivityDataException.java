package pl.service.detector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncompleteUserActivityDataException extends RuntimeException {

    public IncompleteUserActivityDataException(String message) {
        super(message);
    }
}
