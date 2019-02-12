package com.AdminPanel.Angular5SpringBoot.controller;

import com.AdminPanel.Angular5SpringBoot.model.User;
import com.AdminPanel.Angular5SpringBoot.service.EmailService;
import com.AdminPanel.Angular5SpringBoot.service.UserService;
import com.AdminPanel.Angular5SpringBoot.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class MainController {

    private final UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private final EmailService emailService;

    private final UserDetailsService userDetailsService;

    private final UserValidator userValidator;

    @Autowired
    public MainController(UserDetailsService userDetailsService, EmailService emailService, UserService userService, UserValidator userValidator) {
        this.userDetailsService = userDetailsService;
        this.emailService = emailService;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> login(@Valid @RequestBody User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);
        if (!bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        User userFromDB = userService.findByUsername(user.getUsername());
        if (user.getPassword().equals(userFromDB.getPassword())) {
            LOGGER.info("User " + user.getUsername() + " is logged in.");
            LOGGER.info("Password user is " + user.getPassword());
            LOGGER.info("User has role as " + userFromDB.getAuthorities());
            userService.setUserToken(userFromDB);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>(userFromDB, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<String> logout() {
        User user = userService.findByUsername("anonymous");
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOGGER.info("User had logout.");
            return new ResponseEntity<>("User had logout.", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<String> registerForm(@RequestBody User userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        LOGGER.info(userForm.getUsername() + " : " + userForm.getPassword());

        userService.save(userForm);
        LOGGER.info("User - " + userForm.getUsername() + " - is saved.");

        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            LOGGER.info("User " + userForm.getUsername() + " is logged in.");
            userService.setUserToken(userForm);
            LOGGER.info(userForm.getToken());
            return new ResponseEntity<>(userForm.getToken(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Not logged", HttpStatus.NOT_ACCEPTABLE);
    }


//    @PostMapping(value = "/auth/forgotPassword")
//    public ResponseEntity<String> getNewPassword(@RequestBody String userEmail, @RequestHeader String ) {
//        if (userEmail.equals(userService.findByEmail())) {
//            return new ResponseEntity<>("Send message", HttpStatus.OK);
//        }
//    }
}
