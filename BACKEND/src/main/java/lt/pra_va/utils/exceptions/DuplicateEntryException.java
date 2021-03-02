package lt.pra_va.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lt.pra_va.utils.common.FieldErrorContainer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Exception that should be thrown when user is trying to persist element with duplicate property.
 */
@ResponseStatus(HttpStatus.CONFLICT)
@AllArgsConstructor
@Getter
public class DuplicateEntryException extends RuntimeException {
    private List<FieldErrorContainer> fieldErrorContainerList;
}
