package com.perficient.simplecrudproject.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.concurrent.ExecutionException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, NumberFormatException.class,
            HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessage> badRequest(Exception ex, WebRequest request) {
        log.error(ex.toString());
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(NoSuchPersonException.class)
    public ResponseEntity<ErrorMessage> personNotFound(NoSuchPersonException ex, WebRequest request) {
        log.error(ex.toString());
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler({InterruptedException.class, ExecutionException.class,Exception.class})
    public ResponseEntity<ErrorMessage> internalServerError(Exception ex, WebRequest request) {
        log.error(ex.toString());
        return ResponseEntity.internalServerError().body(new ErrorMessage(ex.getMessage()));
    }

}
