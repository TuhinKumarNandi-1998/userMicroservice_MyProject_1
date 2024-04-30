package com.example.userservicemyproject_1.Controllers;

import com.example.userservicemyproject_1.Exceptions.InvalidTokenException;
import com.example.userservicemyproject_1.Exceptions.UserNotFoundException;
import com.example.userservicemyproject_1.Exceptions.WrongPasswordException;
import com.example.userservicemyproject_1.Models.Tokens;
import com.example.userservicemyproject_1.Models.Users;
import com.example.userservicemyproject_1.Services.UserService;
import com.example.userservicemyproject_1.dtos.LoginRequestDTO;
import com.example.userservicemyproject_1.dtos.LogoutRequestDTO;
import com.example.userservicemyproject_1.dtos.SignUpRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public Users signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        String email = signUpRequestDTO.getEmail();
        String password = signUpRequestDTO.getPassword();
        String name = signUpRequestDTO.getName();


        return userService.signUp(email, password, name);
    }

    @PostMapping("/login")
    public Tokens login(@RequestBody LoginRequestDTO loginRequestDTO) throws UserNotFoundException, WrongPasswordException {
        return userService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) throws InvalidTokenException {
        userService.logout(logoutRequestDTO.getTokenValue());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
