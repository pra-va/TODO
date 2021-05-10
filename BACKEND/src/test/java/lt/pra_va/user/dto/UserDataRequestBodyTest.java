package lt.pra_va.user.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UserDataRequestBodyTest {

    public static final String USERNAME_SIZE = "{username.size}";
    public static final String USERNAME_REQUIRED = "{username.required}";
    public static final String USERNAME_PATTERN_SPACES = "{username.pattern.spaces}";
    public static final String PASSWORD_SIZE = "{password.size}";
    public static final String PASSWORD_REQUIRED = "{password.required}";
    public static final String PASSWORD_PATTERN_NUMBER = "{password.pattern.number}";
    public static final String PASSWORD_PATTERN_UPPER_LETTER = "{password.pattern.upperLetter}";
    public static final String PASSWORD_PATTERN_LOWER_LETTER = "{password.pattern.lowerLetter}";
    public static final String PASSWORD_PATTERN_SPACES = "{password.pattern.spaces}";
    public static final String EMAIL_FORMAT = "{email.format}";
    public static final String EMAIL_REQUIRED = "{email.required}";

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private UserDataRequestBody userData;

    @BeforeEach
    public void beforeEach() {
        this.userData = new UserDataRequestBody();
    }

    public List<String> getErrorTemplates(Set<ConstraintViolation<UserDataRequestBody>> violations) {
        return violations.stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList());
    }

    public boolean isListContainsMessage(List<String> violations, String message) {
        return violations.stream().anyMatch(violation -> violation.equals(message));
    }

    @Test
    void userDataRequest_allFieldsNull() {
        String ERROR_MESSAGE_USERNAME = "Null username should produce violation.";
        String ERROR_MESSAGE_PASSWORD = "Null password should produce violation.";
        String ERROR_MESSAGE_EMAIL = "Null email should produce violation.";

        List<String> violations = getErrorTemplates(validator.validate(userData));

        assertTrue(isListContainsMessage(violations, USERNAME_REQUIRED), ERROR_MESSAGE_USERNAME);
        assertTrue(isListContainsMessage(violations, EMAIL_REQUIRED), ERROR_MESSAGE_EMAIL);
        assertTrue(isListContainsMessage(violations, PASSWORD_REQUIRED), ERROR_MESSAGE_PASSWORD);
    }

    @Test
    void userDataRequest_usernameAndPasswordShort() {
        String ERROR_MESSAGE_USERNAME_SHORT = "Username shorter than 5 symbols should produce violation.";
        String ERROR_MESSAGE_PASSWORD_SHORT = "Password shorter than 8 symbols should produce violation.";

        String shortUsername = "sduS";
        String shortPassword = "XYDnJNm";
        this.userData.setUsername(shortUsername);
        this.userData.setPassword(shortPassword);
        List<String> violations = getErrorTemplates(validator.validate(userData));

        assertTrue(isListContainsMessage(violations, USERNAME_SIZE), ERROR_MESSAGE_USERNAME_SHORT);
        assertTrue(isListContainsMessage(violations, PASSWORD_SIZE), ERROR_MESSAGE_PASSWORD_SHORT);
    }

    @Test
    void userDataRequest_usernameAndPasswordLong() {
        String ERROR_MESSAGE_USERNAME_LONG = "Username longer than 20 symbols should produce violation.";
        String ERROR_MESSAGE_PASSWORD_LONG = "Password longer than 40 symbols should produce violation.";

        String longUsername = "mZSjavGXIKzBRhwfb0rfC";
        String longPassword = "T2ABpkgkdIVMTpeG6Zbi0Lviwjiy9IOhLlyfy6Kcl";
        this.userData.setUsername(longUsername);
        this.userData.setPassword(longPassword);
        List<String> violations = getErrorTemplates(validator.validate(userData));

        assertTrue(isListContainsMessage(violations, USERNAME_SIZE), ERROR_MESSAGE_USERNAME_LONG);
        assertTrue(isListContainsMessage(violations, PASSWORD_SIZE), ERROR_MESSAGE_PASSWORD_LONG);
    }

    @Test
    void userDataRequest_emailWrongFormat() {
        String ERROR_MESSAGE = "Wrong email format should produce error.";

        this.userData.setEmail("email");
        List<String> violations = getErrorTemplates(validator.validate(userData));

        assertTrue(isListContainsMessage(violations, EMAIL_FORMAT), ERROR_MESSAGE);
    }

    @Test
    void userDataRequest_passwordPatternsWrong() {
        String ERROR_MESSAGE_NO_NUMBER = "Password without number should produce error.";
        String ERROR_MESSAGE_NO_LOWERCASE = "Password without lowercase letter should produce error.";
        String ERROR_MESSAGE_NO_UPPERCASE = "Password without uppercase letter should produce error.";
        String ERROR_MESSAGE_SPACE = "Password with space should produce error.";

        String passwordWrongNumberUppercaseSpace = "coronavirus ";
        String passwordLower = "CORONAVIRUS";
        userData.setPassword(passwordWrongNumberUppercaseSpace);
        UserDataRequestBody upperCaseViolationRequest = new UserDataRequestBody(null, passwordLower, null);
        List<String> violationsNumberUpperSpace = getErrorTemplates(validator.validate(userData));
        List<String> violationsLower = getErrorTemplates(validator.validate(upperCaseViolationRequest));

        assertTrue(isListContainsMessage(violationsNumberUpperSpace, PASSWORD_PATTERN_NUMBER), ERROR_MESSAGE_NO_NUMBER);
        assertTrue(isListContainsMessage(violationsNumberUpperSpace, PASSWORD_PATTERN_UPPER_LETTER), ERROR_MESSAGE_NO_UPPERCASE);
        assertTrue(isListContainsMessage(violationsNumberUpperSpace, PASSWORD_PATTERN_SPACES), ERROR_MESSAGE_SPACE);
        assertTrue(isListContainsMessage(violationsLower, PASSWORD_PATTERN_LOWER_LETTER), ERROR_MESSAGE_NO_LOWERCASE);
    }

    @Test
    void userDataRequest_usernamePatternWrong() {
        String ERROR_MESSAGE = "Username with space should produce error.";

        userData.setUsername("asd fgh");
        List<String> violations = getErrorTemplates(validator.validate(userData));

        assertTrue(isListContainsMessage(violations, USERNAME_PATTERN_SPACES), ERROR_MESSAGE);
    }

    @Test
    void userDataRequest_correctRequest() {
        String ERROR_MESSAGE = "User data request with correct data should produce no errors.";

        userData.setPassword("Asdf1234");
        userData.setUsername("username");
        userData.setEmail("asdf@gmail.com");
        List<String> violations = getErrorTemplates(validator.validate(userData));

        assertTrue(violations.isEmpty(), ERROR_MESSAGE);
    }

}