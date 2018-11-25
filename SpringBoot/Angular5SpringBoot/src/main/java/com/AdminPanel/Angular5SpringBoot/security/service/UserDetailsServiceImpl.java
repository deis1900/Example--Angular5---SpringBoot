package com.AdminPanel.Angular5SpringBoot.security.service;

import com.AdminPanel.Angular5SpringBoot.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        System.out.println(username + " is username");
        System.out.println(userRepository.findUserByUsername(username).getToken());
        System.out.println(userRepository.findUserByUsername(username).getUsername());
        System.out.println(userRepository.findUserByUsername(username).getPassword());
        System.out.println(userRepository.findUserByUsername(username).getAuthorities());


        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User " + username + " was not found. "));
    }
}
