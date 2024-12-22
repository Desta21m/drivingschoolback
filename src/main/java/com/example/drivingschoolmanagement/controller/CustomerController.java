package com.example.drivingschoolmanagement.controller;

import com.example.drivingschoolmanagement.model.Customer;
import com.example.drivingschoolmanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create or Update Customer
    @PostMapping
    public ResponseEntity<Customer> createOrUpdateCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Find customer by phone number
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<Customer> getCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        Customer customer = customerService.getCustomerByPhoneNumber(phoneNumber);
        return customer != null ? ResponseEntity.ok(customer)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
