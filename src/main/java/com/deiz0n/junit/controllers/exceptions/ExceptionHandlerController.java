package com.deiz0n.junit.controllers.exceptions;

import com.deiz0n.junit.services.exceptions.FieldExistingException;
import com.deiz0n.junit.services.exceptions.ResourceNotFoundException;
import com.deiz0n.junit.services.exceptions.StandartError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> entityNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        var error = new StandartError(
                Instant.now(),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(FieldExistingException.class)
    public ResponseEntity<?> fieldExisting(FieldExistingException exception, HttpServletRequest request) {
        var error = new StandartError(
                Instant.now(),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(400).body(error);
    }

}
