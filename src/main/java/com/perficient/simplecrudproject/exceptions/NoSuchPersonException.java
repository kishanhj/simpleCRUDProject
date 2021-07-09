package com.perficient.simplecrudproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Person not Found")
public class NoSuchPersonException extends RuntimeException{
    public NoSuchPersonException(String message) {
        super(message);
    }
}
