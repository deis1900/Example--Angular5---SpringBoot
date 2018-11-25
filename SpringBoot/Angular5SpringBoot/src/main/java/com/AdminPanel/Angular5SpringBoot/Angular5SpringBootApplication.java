package com.AdminPanel.Angular5SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Angular5SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Angular5SpringBootApplication.class, args);
    }

}
