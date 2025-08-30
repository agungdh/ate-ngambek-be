package id.my.agungdh.atengambekbe.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;

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

    // Untuk @Validated di @RequestParam, @PathVariable, dll.
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", String.format("Constraint violation. Error count: %d", ex.getConstraintViolations().size()));
        body.put("errors", toConstraintViolations(ex));
        body.put("path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Untuk kasus binding error non-JSON (e.g. form-data), mirip MethodArgumentNotValid
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(
            BindException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", String.format(
                "Validation failed for object='%s'. Error count: %d",
                ex.getBindingResult().getObjectName(),
                ex.getBindingResult().getErrorCount()
        ));
        body.put("errors", toFieldErrors(ex));
        body.put("path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // ===== Helper methods =====

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

    private List<Map<String, Object>> toFieldErrors(BindException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    Map<String, Object> m = new LinkedHashMap<>();
                    m.put("objectName", error.getObjectName());

                    if (error instanceof FieldError fe) {
                        m.put("field", fe.getField());
                        m.put("rejectedValue", fe.getRejectedValue());
                        m.put("bindingFailure", fe.isBindingFailure());
                    } else {
                        m.put("field", null);
                        m.put("rejectedValue", null);
                        m.put("bindingFailure", false);
                    }

                    m.put("codes", Optional.ofNullable(error.getCodes()).map(Arrays::asList).orElse(null));
                    m.put("arguments", Optional.ofNullable(error.getArguments()).map(Arrays::asList).orElse(null));
                    m.put("defaultMessage", error.getDefaultMessage());
                    m.put("code", error.getCode());
                    return m;
                })
                .toList();
    }

    private List<Map<String, Object>> toConstraintViolations(ConstraintViolationException ex) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ConstraintViolation<?> v : ex.getConstraintViolations()) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("objectName", v.getRootBeanClass() != null ? v.getRootBeanClass().getSimpleName() : null);
            m.put("field", v.getPropertyPath() != null ? v.getPropertyPath().toString() : null);
            m.put("rejectedValue", v.getInvalidValue());
            m.put("codes", List.of(v.getMessageTemplate())); // biasanya berisi kode seperti "{NotBlank}"
            m.put("arguments", null);
            m.put("message", v.getMessage());
            m.put("bindingFailure", false);
            m.put("code", extractCodeFromTemplate(v.getMessageTemplate()));
            list.add(m);
        }
        return list;
    }

    private String extractCodeFromTemplate(String template) {
        // Ubah "{NotBlank}" -> "NotBlank"
        if (template == null) return null;
        if (template.startsWith("{") && template.endsWith("}") && template.length() > 2) {
            return template.substring(1, template.length() - 1);
        }
        return template;
    }
}
