package com.AdminPanel.Angular5SpringBoot.service;

import com.AdminPanel.Angular5SpringBoot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findByUserWhereAccountNonLocked(Boolean varLock);

    List<User> findAll();

    void save(User user);

    Optional<User> findByEmail(String email);

    User findByUsername(String username);

    String setUserToken(User user);

}
