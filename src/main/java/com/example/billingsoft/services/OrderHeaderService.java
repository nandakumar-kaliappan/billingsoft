package com.example.billingsoft.services;

import com.example.billingsoft.domain.OrderHeader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderHeaderService {
    List<OrderHeader> getAllOrders();
}