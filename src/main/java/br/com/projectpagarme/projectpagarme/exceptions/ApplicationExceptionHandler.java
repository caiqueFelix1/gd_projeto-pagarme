package br.com.projectpagarme.projectpagarme.exceptions;

import br.com.projectpagarme.projectpagarme.exceptions.models.ErrorObject;
import br.com.projectpagarme.projectpagarme.exceptions.models.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(status, errors);
        log.error("an error occurred in data entry: {}", errorResponse);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse getErrorResponse(HttpStatus status, List<ErrorObject> errors) {
        return new ErrorResponse(Instant.now().toEpochMilli(),
                status.value(),
                status.getReasonPhrase(),
                "the request has invalid fields",
                errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
}
