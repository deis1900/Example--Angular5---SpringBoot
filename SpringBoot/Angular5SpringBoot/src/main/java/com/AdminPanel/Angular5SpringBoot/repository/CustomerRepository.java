package com.AdminPanel.Angular5SpringBoot.repository;

import com.AdminPanel.Angular5SpringBoot.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUserName(String userName);
    Customer findByEmail(String email);
}
