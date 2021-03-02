package lt.pra_va.utils;

import lt.pra_va.utils.common.FieldErrorContainer;
import lt.pra_va.utils.exceptions.DuplicateEntryException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

/**
 * This class allows to handle specified types of exceptions through methods provided here.
 */
@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {
    /**
     * Handle bad request exception that is going to be triggered if entities constrains are going to be violated.
     * @param ex - exception.
     * @param headers - http headers.
     * @param status - http status.
     * @param request - web request.
     * @return map of fields and error messages.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<FieldErrorContainer> errors = new ArrayList<>();
        
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                        Optional.of(errors)
                                .map(errorContainers -> errorContainers.stream()
                                        .filter(errorContainer -> errorContainer.getField().equals(fieldError.getField()))
                                        .findFirst()
                                        .map(errorContainer -> errorContainer.addMessage(fieldError.getDefaultMessage()))
                                        .orElse(
                                                Optional.of(
                                                        new FieldErrorContainer(fieldError.getField(),
                                                                                fieldError.getRejectedValue(),
                                                                                Collections.singletonList(
                                                                                        fieldError.getDefaultMessage()))
                                                                                )
                                                    .map(errorContainer -> {
                                                        errors.add(errorContainer);
                                                        return errorContainer;
                                                    })
                                                .get()
                                        )
                                );
                });

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(value = DuplicateEntryException.class)
    protected ResponseEntity<Object> handleDuplicateEntryException(DuplicateEntryException ex,
                                                                   WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", "409");
        body.put("errors", ex.getFieldErrorContainerList());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

}
