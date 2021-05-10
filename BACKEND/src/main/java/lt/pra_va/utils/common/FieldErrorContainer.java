package lt.pra_va.utils.common;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Container POJO class to carry error information.
 */
@Getter
public class FieldErrorContainer {
    private final String field;
    private final Object value;
    private final List<String> messages = new ArrayList<>();

    public FieldErrorContainer(String field, Object value, String initialMessage) {
        this.field = field;
        this.value = value;
        addMessage(initialMessage);
    }

    public FieldErrorContainer addMessage(String message) {
        this.messages.add(message);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if(obj.getClass() != this.getClass()) return false;

        FieldErrorContainer errorContainer = (FieldErrorContainer) obj;

        return errorContainer.getField().equals(this.field);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((this.field == null) ? 0 : this.field.hashCode());
        return result;

    }
}
