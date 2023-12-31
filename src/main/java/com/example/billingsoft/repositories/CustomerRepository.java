package com.example.billingsoft.repositories;

import com.example.billingsoft.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
