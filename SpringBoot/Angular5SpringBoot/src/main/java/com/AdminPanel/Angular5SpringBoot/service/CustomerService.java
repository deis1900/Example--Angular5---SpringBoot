package com.AdminPanel.Angular5SpringBoot.service;

import com.AdminPanel.Angular5SpringBoot.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    void save(Customer customer);

    List<Customer> saveAll(List<Customer> customerList);

    Customer findById(Long id);

    Boolean isCustomerExist(Customer customer);

    void updateCustomer(Customer customer);

    Customer findByUserName(String userName);

    void deleteCustomer(Long id);
}
