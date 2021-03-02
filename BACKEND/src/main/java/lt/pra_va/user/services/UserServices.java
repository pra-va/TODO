package lt.pra_va.user.services;

import lt.pra_va.user.dao.UserRepository;
import lt.pra_va.user.dto.UserDto;
import lt.pra_va.user.model.AuthorityType;
import lt.pra_va.user.model.User;
import lt.pra_va.utils.common.FieldErrorContainer;
import lt.pra_va.utils.exceptions.DuplicateEntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;


@Service
public class UserServices {

    private final String USERNAME = "username";
    private final String EMAIL = "email";
    private final String USERNAME_TAKEN = "Username is taken.";
    private final String EMAIL_TAKEN = "Email is taken.";

    @Autowired
    UserRepository repository;

    public ResponseEntity<Object> createUser(UserDto user) {
        ResponseEntity<Object> usernameOrEmailTakenResponse = checkUsernameAndEmail(user.getUsername(), user.getEmail());

        if(usernameOrEmailTakenResponse != null) {
            return usernameOrEmailTakenResponse;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ArrayList<AuthorityType> authorities = new ArrayList<>();
        authorities.add(AuthorityType.USER_AUTHORITY);

        User dbCustomUser = new User(
                new Date(new java.util.Date().getTime()),
                new Date(new java.util.Date().getTime()),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                authorities,
                true,
                true,
                true,
                true
        );

        repository.save(dbCustomUser);
        return new ResponseEntity<>("User has been created.", HttpStatus.CREATED);
    }

    public boolean isUsernameTaken(String username) {
        return repository.existsByUsername(username);
    }

    public boolean isEmailTaken(String email) {
        return repository.existsByEmail(email);
    }

    public ResponseEntity<Object> checkUsernameAndEmail(String username, String email) {
        List<FieldErrorContainer> errorContainerList = new ArrayList<>();

        if (isUsernameTaken(username)) {
            errorContainerList.add(new FieldErrorContainer(USERNAME, username, Collections.singletonList(USERNAME_TAKEN)));
        }

        if (isEmailTaken(email)) {
            errorContainerList.add(new FieldErrorContainer(EMAIL, email, Collections.singletonList(EMAIL_TAKEN)));
        }

        if (errorContainerList .size() > 0) {
            throw new DuplicateEntryException(errorContainerList);
        }

        return null;
    }

}



















