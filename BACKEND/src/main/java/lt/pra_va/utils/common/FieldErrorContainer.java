package lt.pra_va.utils.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Container POJO class to carry error information.
 */
@AllArgsConstructor
@Getter
public class FieldErrorContainer {
    private final String field;
    private final Object value;
    private final List<String> messages;

    public FieldErrorContainer addMessage(String message) {
        this.messages.add(message);
        return this;
    }
}
