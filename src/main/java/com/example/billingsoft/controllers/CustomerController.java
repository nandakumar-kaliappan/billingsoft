package com.example.billingsoft.controllers;

import com.example.billingsoft.domain.OrderHeader;
import com.example.billingsoft.domain.OrderLine;
import com.example.billingsoft.domain.Customer;
import com.example.billingsoft.services.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/v1/customer")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("api/v1/customer/{id}")
    public Customer getACustomer(@PathVariable("id") Long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping("/api/v1/customer")
    public ResponseEntity addNewCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.addNewCustomer(customer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/customer/" + newCustomer.getId());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/v1/customer/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity(httpHeaders, HttpStatus.NO_CONTENT);
    }

}
