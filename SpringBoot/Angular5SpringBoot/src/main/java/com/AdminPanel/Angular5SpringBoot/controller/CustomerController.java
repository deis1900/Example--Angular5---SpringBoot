package com.AdminPanel.Angular5SpringBoot.controller;

import com.AdminPanel.Angular5SpringBoot.dto.CustomerDto;
import com.AdminPanel.Angular5SpringBoot.fileWorkspace.CsvManager;
import com.AdminPanel.Angular5SpringBoot.fileWorkspace.modelConvertors.CustomerRows;
import com.AdminPanel.Angular5SpringBoot.model.Customer;
import com.AdminPanel.Angular5SpringBoot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getAll() {
        List<Customer> customerList = customerService.findAll();
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CustomerDto wrapper = new CustomerDto();
        wrapper.setListCustomers(customerList);
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getUser(@PathVariable("id") Long id) {
        System.out.println("Fetching Customer with id " + id);
        Customer customer = customerService.findById(id);
        if (customer == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(value = "/login/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByUserName(@PathVariable String userName) {
        Customer customer = customerService.findByUserName(userName);
        if (customer == null) {
            System.out.println("Unable to find. Customer with userName " + userName + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postCustomer(@RequestBody Customer customer) {
        System.out.println("Creating Customer " + customer.getUserName());
        if (customerService.isCustomerExist(customer)) {
            System.out.println("A Customer with name " + customer.getUserName() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        customerService.save(new Customer(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUserName(),
                customer.getPassword(),
                customer.getEmail(),
                customer.getGender(),
                customer.getPhone(),
                customer.getAccess(),
                customer.getImage()));
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> singleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        List<String> csvRows = new CsvManager().getRows(file);
        if (csvRows == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        List<Customer> customers = new CustomerRows(csvRows).convertRowsToCustomers();
        if (customers == null) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity<>(customerService.saveAll(customers), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        System.out.println("Update customer with id " + id);
        Customer cust = customerService.findById(id);
        if (cust == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (customer.equals(customerService.findById(customer.getId()))) {
            System.out.println("A Customer with " + customer.getId() + " already exist ");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        customerService.updateCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting Customer with id " + id);
        Customer customer = customerService.findById(id);
        if (customer == null) {
            System.out.println("Unable to delete. Customer with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
