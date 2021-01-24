package lt.pra_va.user.controller;

import lt.pra_va.user.dto.UserDataRequestBody;
import lt.pra_va.user.dto.UserDto;
import lt.pra_va.user.services.UserServices;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices services;

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDataRequestBody userData) {
        return services.createUser(new UserDto(userData.getUsername(), userData.getPassword(), userData.getEmail()));
    }

    @PostMapping("/username/{username}")
    public ResponseEntity<Object> isUsernameTaken(@PathVariable String username) {
        boolean isUsernameTaken = services.isUsernameTaken(username);
        if (isUsernameTaken) {
            return new ResponseEntity<>("username is taken", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/email/{email}")
    public ResponseEntity<Object> isEmailTaken(@PathVariable String email) {
        boolean isEmailTaken = services.isUsernameTaken(email);
        if (isEmailTaken) {
            return new ResponseEntity<>("email is taken", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
