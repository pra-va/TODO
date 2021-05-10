package lt.pra_va.utils;

import lt.pra_va.utils.common.FieldErrorContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExceptionsControllerTest {

    public static final String ERRORS_KEY = "errors";

    public static final String OBJECT_NAME = "OBJECT_NAME";
    public static final String FIELD_1 = "FIELD_1";
    public static final String FIELD_2 = "FIELD_2";
    public static final String DEFAULT_MESSAGE_1 = "DEFAULT_MESSAGE_1";
    public static final String DEFAULT_MESSAGE_2 = "DEFAULT_MESSAGE_2";
    public static final String DEFAULT_MESSAGE_3 = "DEFAULT_MESSAGE_3";

    @Mock
    private MethodArgumentNotValidException exception;

    @Mock
    private HttpHeaders headers;

    @Mock
    private WebRequest webRequest;

    @Mock
    private DirectFieldBindingResult bindingResult;

    private final HttpStatus status = HttpStatus.OK;

    private final ExceptionsController exceptionsController = new ExceptionsController();

    @SuppressWarnings("unchecked")
    public List<FieldErrorContainer> getExceptionsResponseBody(List<FieldError> fieldErrorList) {
        Mockito.when(exception.getBindingResult()).thenReturn(bindingResult);
        Mockito.when(exception.getBindingResult().getFieldErrors()).thenReturn(fieldErrorList);
        Map<String, Object> responseBody = (Map<String, Object>) exceptionsController
                .handleMethodArgumentNotValid(exception, headers, status, webRequest)
                .getBody();

        assert responseBody != null;
        assert responseBody.containsKey(ERRORS_KEY);
        return (List<FieldErrorContainer>) responseBody.get(ERRORS_KEY);
    }

    public List<String> getErrorMessages(List<FieldErrorContainer> errorContainers) {
        return errorContainers
                .stream()
                .map(FieldErrorContainer::getMessages)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<FieldErrorContainer> findFieldErrorContainerByFieldName(String fieldName, List<FieldErrorContainer> errors) {
        return errors.stream().filter(errorContainer -> errorContainer.getField().equals(fieldName)).collect(Collectors.toList());
    }

    @Test
    public void handleMethodArgumentNotValid_containsMessages_twoFields_threeErrorMessages() {
        String ERROR_MESSAGE = "Returned error containers should contain error messages.";

        List<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new FieldError(OBJECT_NAME, FIELD_1, DEFAULT_MESSAGE_1));
        fieldErrorList.add(new FieldError(OBJECT_NAME,  FIELD_1, DEFAULT_MESSAGE_2));
        fieldErrorList.add(new FieldError(OBJECT_NAME, FIELD_2, DEFAULT_MESSAGE_3));
        List<String> errorMessages = getErrorMessages(getExceptionsResponseBody(fieldErrorList));

        assertTrue(errorMessages.contains(DEFAULT_MESSAGE_1), ERROR_MESSAGE);
        assertTrue(errorMessages.contains(DEFAULT_MESSAGE_2), ERROR_MESSAGE);
        assertTrue(errorMessages.contains(DEFAULT_MESSAGE_3), ERROR_MESSAGE);
    }

    @Test
    public void handleMethodArgumentNotValid_containsMessages_oneField_oneErrorMessage() {
        String ERROR_MESSAGE = "Returned error containers should contain error messages.";

        List<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new FieldError(OBJECT_NAME, FIELD_1, DEFAULT_MESSAGE_1));
        List<String> errorMessages = getErrorMessages(getExceptionsResponseBody(fieldErrorList));
        assertTrue(errorMessages.contains(DEFAULT_MESSAGE_1), ERROR_MESSAGE);
    }

    @Test
    public void handleMethodArgumentNotValid_containsMessages_noField_noErrorMessage() {
        String ERROR_MESSAGE = "There should be no error containers returned, if there are no error messages.";

        List<FieldError> fieldErrorList = new ArrayList<>();
        List<String> errorMessages = getErrorMessages(getExceptionsResponseBody(fieldErrorList));
        assertTrue(errorMessages.isEmpty(), ERROR_MESSAGE);
    }

    @Test
    public void handleMethodArgumentNotValid_testContainerFields_twoFields_threeErrorMessages() {
        String ERROR_MESSAGE = "After aggregation, there should be a correct error containers count inside aggregated list.";

        List<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new FieldError(OBJECT_NAME, FIELD_1, DEFAULT_MESSAGE_1));
        fieldErrorList.add(new FieldError(OBJECT_NAME,  FIELD_1, DEFAULT_MESSAGE_2));
        fieldErrorList.add(new FieldError(OBJECT_NAME, FIELD_2, DEFAULT_MESSAGE_3));
        List<FieldErrorContainer> errorMessages = getExceptionsResponseBody(fieldErrorList);

        assertEquals(2, errorMessages.size(), ERROR_MESSAGE);
    }

    @Test
    public void handleMethodArgumentNotValid_testErrorMessagesByContainer_twoFields_threeErrorMessages() {
        String ERROR_MESSAGE = "Error messages should be aggregated to their respective error containers.";

        List<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new FieldError(OBJECT_NAME, FIELD_1, DEFAULT_MESSAGE_1));
        fieldErrorList.add(new FieldError(OBJECT_NAME,  FIELD_1, DEFAULT_MESSAGE_2));
        fieldErrorList.add(new FieldError(OBJECT_NAME, FIELD_2, DEFAULT_MESSAGE_3));
        List<FieldErrorContainer> errorMessages = getExceptionsResponseBody(fieldErrorList);

        int field1ErrorMessagesLength = findFieldErrorContainerByFieldName(FIELD_1, errorMessages).get(0).getMessages().size();
        int field2ErrorMessagesLength = findFieldErrorContainerByFieldName(FIELD_2, errorMessages).get(0).getMessages().size();

        assertEquals(2, field1ErrorMessagesLength, ERROR_MESSAGE);
        assertEquals(1, field2ErrorMessagesLength, ERROR_MESSAGE);
    }

    @Test
    public void handleMethodArgumentNotValid_testErrorMessagesAggregatedCorrectly_twoFields_threeErrorMessages() {
        String ERROR_MESSAGE = "Error messages with same field should be aggregated to the same container.";

        List<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new FieldError(OBJECT_NAME, FIELD_1, DEFAULT_MESSAGE_1));
        fieldErrorList.add(new FieldError(OBJECT_NAME,  FIELD_1, DEFAULT_MESSAGE_2));
        fieldErrorList.add(new FieldError(OBJECT_NAME, FIELD_2, DEFAULT_MESSAGE_3));
        List<FieldErrorContainer> errorMessages = getExceptionsResponseBody(fieldErrorList);

        int field1ErrorContainersLength = findFieldErrorContainerByFieldName(FIELD_1, errorMessages).size();
        int field2ErrorContainersLength = findFieldErrorContainerByFieldName(FIELD_2, errorMessages).size();

        assertEquals(1, field1ErrorContainersLength, ERROR_MESSAGE);
        assertEquals(1, field2ErrorContainersLength, ERROR_MESSAGE);
    }
}