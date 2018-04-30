package com.AdminPanel.Angular5SpringBoot.repository;

import com.AdminPanel.Angular5SpringBoot.model.User;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@NonNull String username);
}
