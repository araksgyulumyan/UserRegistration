package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by araksgyulumyan
 * Date - 4/18/18
 * Time - 5:57 PM
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST,
        reason = "Photo Not Found")
public class FieldNotFoundException extends RuntimeException {

    public FieldNotFoundException() {
        super();
    }

    public FieldNotFoundException(String message) {
        super(message);
    }

    public FieldNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldNotFoundException(Throwable cause) {
        super(cause);
    }
}
