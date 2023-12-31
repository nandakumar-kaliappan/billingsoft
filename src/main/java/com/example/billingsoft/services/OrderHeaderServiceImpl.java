package com.example.billingsoft.services;

import com.example.billingsoft.domain.OrderHeader;
import com.example.billingsoft.repositories.OrderHeaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderHeaderServiceImpl implements OrderHeaderService {
    private final OrderHeaderRepository orderHeaderRepository;

    public OrderHeaderServiceImpl(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    @Override
    public List<OrderHeader> getAllOrders() {
        return orderHeaderRepository.findAll();
    }

    @Override
    @Transactional
    public OrderHeader saveOrder(OrderHeader orderHeader) {
        return orderHeaderRepository.saveAndFlush(orderHeader);
    }

    @Override
    public OrderHeader getOrder(Long orderId) {
        return orderHeaderRepository.findById(orderId).orElse(OrderHeader.builder().build());
    }

    @Override
    @Transactional
    public OrderHeader updateOrder(Long id, OrderHeader orderHeader) {
        if(id!=orderHeader.getId()){
            throw new IllegalArgumentException("OrderHeader has mismatched id");
        }
        return orderHeaderRepository.saveAndFlush(orderHeader);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderHeaderRepository.delete(orderHeaderRepository.
                findById(id)
                .orElseThrow(()->new IllegalArgumentException()));
    }
}
