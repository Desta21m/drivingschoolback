package com.example.drivingschoolmanagement.service;

import com.example.drivingschoolmanagement.model.Customer;
import com.example.drivingschoolmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Create or Update Customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get a customer by ID
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    // Delete customer by ID
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    // Find customer by phone number
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber).get();
    }
}
