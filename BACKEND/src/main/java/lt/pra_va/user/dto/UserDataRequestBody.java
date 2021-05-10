package lt.pra_va.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDataRequestBody {
    @NotEmpty(message = "{username.required}")
    @Size(min = 5, max = 20, message = "{username.size}")
    @Pattern(regexp = "^(?=\\S+$).+$", message = "{username.pattern.spaces}")
    private String username;

    @NotEmpty(message = "{password.required}")
    @Size(min = 8, max = 40, message = "{password.size}")
    @Pattern(regexp = "^(?=.*[0-9]).+$", message = "{password.pattern.number}")
    @Pattern(regexp = "^(?=.*[A-Z]).+$", message = "{password.pattern.upperLetter}")
    @Pattern(regexp = "^(?=.*[a-z]).+$", message = "{password.pattern.lowerLetter}")
    @Pattern(regexp = "^(?=\\S+$).+$", message = "{password.pattern.spaces}")
    private String password;

    @Email(message = "{email.format}")
    @NotEmpty(message = "{email.required}")
    private String email;
}
