package lt.pra_va.user.services;

import lt.pra_va.user.dao.UserRepository;
import lt.pra_va.user.dto.UserDto;
import lt.pra_va.user.model.AuthorityType;
import lt.pra_va.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServices {

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
        Map<String, String> responseMap = new HashMap<>();

        if (isUsernameTaken(username)) {
            responseMap.put("username", "username is taken");
        }

        if (isEmailTaken(email)) {
            responseMap.put("email", "email is taken");
        }

        if (responseMap.size() > 0) {
            return new ResponseEntity<>(responseMap, HttpStatus.CONFLICT);
        }

        return null;
    }

}



















