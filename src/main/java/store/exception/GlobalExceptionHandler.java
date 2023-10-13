package store.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        List<String> errorMessages = ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage).collect(Collectors.toList());
        HashMap<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("timestamp", LocalDateTime.now());
        body.put("errors", errorMessages);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(value = RegistrationException.class)
    public ResponseEntity<Object> handleRegistrationException(
            RegistrationException exception,
            WebRequest request) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("timestamp", LocalDateTime.now());
        body.put("error", exception.getMessage());
        return handleExceptionInternal(exception,
                body,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError) {
            String field = ((FieldError) error).getField();
            return field + " " + error.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }
}
