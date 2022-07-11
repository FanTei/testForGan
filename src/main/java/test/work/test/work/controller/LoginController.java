package test.work.test.work.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.work.test.work.entity.User;
import test.work.test.work.exceptions.PasswordException;
import test.work.test.work.exceptions.NotExistsWithUsernameException;
import test.work.test.work.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            userService.getUser(user);
        } catch (PasswordException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (NotExistsWithUsernameException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
