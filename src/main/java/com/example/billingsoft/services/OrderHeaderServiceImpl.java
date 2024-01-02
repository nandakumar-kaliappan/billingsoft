package com.example.billingsoft.services;

import com.example.billingsoft.domain.OrderHeader;
import com.example.billingsoft.domain.OrderLine;
import com.example.billingsoft.repositories.OrderHeaderRepository;
import com.example.billingsoft.repositories.OrderLineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class OrderHeaderServiceImpl implements OrderHeaderService {
    private final OrderHeaderRepository orderHeaderRepository;
    private final OrderLineRepository orderLineRepository;

    public OrderHeaderServiceImpl(OrderHeaderRepository orderHeaderRepository,
                                  OrderLineRepository orderLineRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
        this.orderLineRepository = orderLineRepository;
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
        OrderHeader orderHeaderDB = orderHeaderRepository.findById(id).get();
        orderHeaderDB.getOrderLines().stream()
                .forEach(orderLine -> {
                    orderLineRepository.deleteById(orderLine.getId());
                });
        orderHeaderDB.setOrderLines(new HashSet<>());
        orderHeader.getOrderLines().stream()
                .forEach(orderLine ->{
                    orderHeaderDB.addOrderLine(OrderLine.builder()
                            .product(orderLine.getProduct())
                            .quantity(orderLine.getQuantity())
                            .build());
                });

        return orderHeaderRepository.saveAndFlush(orderHeaderDB);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderHeaderRepository.delete(orderHeaderRepository.
                findById(id)
                .orElseThrow(()->new IllegalArgumentException()));
    }
}
