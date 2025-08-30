package id.my.agungdh.atengambekbe.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", String.format(
                "Validation failed. Error count: %d",
                ex.getBindingResult().getErrorCount()
        ));
        body.put("errors", toFieldErrors(ex));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private List<Map<String, Object>> toFieldErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    Map<String, Object> m = new LinkedHashMap<>();

                    if (error instanceof FieldError fe) {
                        m.put("field", fe.getField());
                    } else {
                        m.put("field", null);
                    }

                    m.put("message", error.getDefaultMessage());
                    m.put("code", error.getCode());
                    return m;
                })
                .toList();
    }
}
