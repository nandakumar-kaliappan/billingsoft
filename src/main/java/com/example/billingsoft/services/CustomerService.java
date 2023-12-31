package com.example.billingsoft.services;

import com.example.billingsoft.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomer(Long id);

    Customer addNewCustomer(Customer customer);

    void deleteCustomer(Long id);


}
