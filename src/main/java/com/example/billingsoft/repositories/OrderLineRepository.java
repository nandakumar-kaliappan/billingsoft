package com.example.billingsoft.repositories;

import com.example.billingsoft.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
