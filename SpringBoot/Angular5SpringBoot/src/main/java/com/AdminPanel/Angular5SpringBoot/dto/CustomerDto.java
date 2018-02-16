package com.AdminPanel.Angular5SpringBoot.dto;

import com.AdminPanel.Angular5SpringBoot.model.Customer;

import java.util.List;

public class CustomerDto {
    private List<Customer> customers;

    public List<Customer> getListCustomers() {
        return customers;
    }

    public void setListCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}