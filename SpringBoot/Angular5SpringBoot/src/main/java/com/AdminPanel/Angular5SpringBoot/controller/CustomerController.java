package com.AdminPanel.Angular5SpringBoot.controller;

import com.AdminPanel.Angular5SpringBoot.dto.CustomerDto;
import com.AdminPanel.Angular5SpringBoot.model.Customer;
import com.AdminPanel.Angular5SpringBoot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping (produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getAll() {
        CustomerDto wrapper  = new CustomerDto();
        wrapper.setListCustomers(customerService.findAll());

        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @PostMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
//        Create test (for successfully download)
        customerService.save(new Customer(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUserName(),
                customer.getPassword(),
                customer.getEmail(),
                customer.getSex(),
                customer.getPhone(),
                customer.getAccess(),
                customer.getImage()));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping (value="/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(value="/username/{userName}",  produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByUserName(@PathVariable String userName) {

        Customer customer = customerService.findByUserName(userName);
        return  new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(value="/email/{email}",  produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByEmail(@PathVariable String email) {

        Customer customer = customerService.findByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public void deleteCustomer(@PathVariable long id){

        customerService.deleteCustomer(id);
    }
}
