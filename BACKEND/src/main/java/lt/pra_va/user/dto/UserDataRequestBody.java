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
    @NotEmpty(message = "username is required")
    @Size(min = 5, max = 20, message = "username has to be between 5 and 20 characters long.")
    private String username;

    @NotEmpty(message = "password is required.")
    @Size(min = 8, max = 40, message = "Password has to be between 8 and 40 characters long.")
    private String password;

    @Email(message = "Email format is not valid.")
    @NotEmpty(message = "Email is required.")
    private String email;
}
