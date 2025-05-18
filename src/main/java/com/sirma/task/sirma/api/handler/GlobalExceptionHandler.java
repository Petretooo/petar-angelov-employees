package com.sirma.task.sirma.api.handler;

import com.sirma.task.sirma.service.validation.exception.CsvException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CsvException.class)
    public ResponseEntity<String> handleValidationException(CsvException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Validation failed: " + ex.getMessage());
    }
}
