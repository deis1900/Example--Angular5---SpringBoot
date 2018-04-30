package com.AdminPanel.Angular5SpringBoot.controller;

import com.AdminPanel.Angular5SpringBoot.SecurityConfiguration;
import com.AdminPanel.Angular5SpringBoot.model.User;
import com.AdminPanel.Angular5SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class MainController {

    private final UserService userService;

    private final SecurityConfiguration securityConfiguration;

    @Autowired
    public MainController(UserService userService, SecurityConfiguration securityConfiguration) {
        this.userService = userService;
        this.securityConfiguration = securityConfiguration;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Token> login(@RequestBody User user) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Token> logout(){return new ResponseEntity<>(HttpStatus.OK);}

    @PostMapping(value = "/registration")
    public ResponseEntity<Token> registerForm(){return new ResponseEntity<>(HttpStatus.OK);}
}
