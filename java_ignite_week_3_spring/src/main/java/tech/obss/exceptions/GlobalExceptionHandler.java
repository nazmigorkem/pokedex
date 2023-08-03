package tech.obss.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(RuntimeException e) {
        LOGGER.error("Runtime exception occurred: {}", e.getMessage());
        var map = Map.of("message", "Access denied.");
        return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        LOGGER.error("Runtime exception occurred: {}", e.getMessage());
        var map = Map.of("message", "Unexpected error occurred.");
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.warn("Validation exception occurred");
        var map = Map.of("message", "Validation exception occurred.");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

}
