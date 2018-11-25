package com.AdminPanel.Angular5SpringBoot.service;

import com.AdminPanel.Angular5SpringBoot.model.Authority;
import com.AdminPanel.Angular5SpringBoot.model.Customer;
import com.AdminPanel.Angular5SpringBoot.model.Role;
import com.AdminPanel.Angular5SpringBoot.model.User;
import com.AdminPanel.Angular5SpringBoot.repository.CustomerRepository;
import com.AdminPanel.Angular5SpringBoot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void save(User user){
        Authority authority = new Authority();
        authority.setRole(Role.USER);
        user.setAuthorities(new ArrayList<Authority>(Collections.singleton(authority)));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        LOGGER.info(user.toString());
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public List<User> findByUserWhereAccountNonLocked(Boolean lockVariable) {
        return userRepository.findByAccountNonLockedLike(lockVariable);
    }

    @Override
    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {

        Customer customer = customerRepository.findByEmail(email);
        if (customer.getUserName() != null) {
            return userRepository.findByUsername(customer.getUserName());
        }

        // Add correct responce;
        return Optional.empty();
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("User " + username + " was not found. "));
    }

    @Override
    @Transactional
    public String setUserToken(User user) {
        user.setToken(
//                bCryptPasswordEncoder.encode(
                user.getUsername() + ":" + user.getPassword()
//        )
        );
        String token = user.getToken();
        userRepository.saveAndFlush(user);
        return token;
    }
}
