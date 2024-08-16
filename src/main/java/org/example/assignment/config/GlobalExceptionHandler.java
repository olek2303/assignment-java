package org.example.assignment.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "status", HttpStatus.NOT_FOUND.value(),
                        "message", ex.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
                Map.of(
                        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "message", "An unexpected error occurred"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
