package lt.pra_va.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDataRequestBody {
    @NotEmpty(message = "{username.required}")
    @Size(min = 5, max = 20, message = "{username.size}")
    private String username;

    @NotEmpty(message = "{password.required}")
    @Size(min = 8, max = 40, message = "{password.size}")
    private String password;

    @Email(message = "{email.format}")
    @NotEmpty(message = "{email.required}")
    private String email;
}
