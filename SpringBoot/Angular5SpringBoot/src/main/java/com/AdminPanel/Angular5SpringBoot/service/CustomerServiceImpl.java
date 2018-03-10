package com.AdminPanel.Angular5SpringBoot.service;

import com.AdminPanel.Angular5SpringBoot.model.Customer;
import com.AdminPanel.Angular5SpringBoot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public Boolean isCustomerExist(Customer customer) {
        return findByEmail(customer.getEmail()) != null;
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void saveAll(List<Customer> customerList) {
        customerRepository.saveAll(customerList);
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer) {
        customerRepository.saveAndFlush(customer);
    }

    @Override
    @Transactional
    public Customer findById(Long id){
        for(Customer customer: customerRepository.findAll()){
            if(customer.getId().equals(id)){
                return customer;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Customer findByUserName(String userName) {
       return customerRepository.findByUserName(userName);
    }

    @Transactional
    public Customer findByEmail(String email) {
       return customerRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
