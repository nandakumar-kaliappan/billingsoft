package com.example.billingsoft.repositories;

import com.example.billingsoft.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

}
