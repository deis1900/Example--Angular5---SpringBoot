package com.AdminPanel.Angular5SpringBoot.repository;

import com.AdminPanel.Angular5SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    List<User> findByAccountNonLockedLike(Boolean lockVar);
    Boolean findByUsernameIsContaining(String username);
    User findUserByUsername(String username);
}
