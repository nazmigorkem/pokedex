package obss.pokemon.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
        List<String> errorList = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(Map.of("errors", errorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        LOGGER.warn("No such element exception occurred");
        var map = Map.of("message", "No such element.");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        LOGGER.warn("Missing servlet request parameter exception occurred");
        var map = Map.of("message", "Missing request parameter. Parameter {" + e.getParameterName() + "} required.");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}