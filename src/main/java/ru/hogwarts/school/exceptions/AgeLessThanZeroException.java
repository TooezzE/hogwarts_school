package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AgeLessThanZeroException extends RuntimeException {
    public AgeLessThanZeroException() {
    }

    public AgeLessThanZeroException(String message) {
        super(message);
    }

    public AgeLessThanZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgeLessThanZeroException(Throwable cause) {
        super(cause);
    }

    public AgeLessThanZeroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
