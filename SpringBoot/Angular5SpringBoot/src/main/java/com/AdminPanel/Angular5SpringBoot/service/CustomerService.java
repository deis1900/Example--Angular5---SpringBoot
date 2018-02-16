package com.AdminPanel.Angular5SpringBoot.service;

import com.AdminPanel.Angular5SpringBoot.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    void save(Customer customer);

    void saveAll(List<Customer> customerList);

    void updateCustomer(Customer customer);

    Customer findByUserName(String userName);

    Customer findByEmail(String email);

    void deleteCustomer(Long id);
}
