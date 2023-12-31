package com.example.billingsoft.services;

import com.example.billingsoft.domain.Customer;
import com.example.billingsoft.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer addNewCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.delete(customerRepository.
                findById(id)
                .orElseThrow(()->new IllegalArgumentException()));
    }
}
