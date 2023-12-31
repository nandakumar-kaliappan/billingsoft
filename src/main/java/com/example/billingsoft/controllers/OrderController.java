package com.example.billingsoft.controllers;

import com.example.billingsoft.domain.OrderHeader;
import com.example.billingsoft.domain.OrderLine;
import com.example.billingsoft.domain.Product;
import com.example.billingsoft.services.OrderHeaderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@RestController
public class OrderController {
    private final OrderHeaderService orderHeaderService;

    public OrderController(OrderHeaderService orderHeaderService) {
        this.orderHeaderService = orderHeaderService;
    }

    @GetMapping("/api/v1/order")
    public List<OrderHeader> getAllOrders() {
        return orderHeaderService.getAllOrders();
    }

    @GetMapping("/api/v1/order/{orderId}")
    public OrderHeader getOrder(@PathVariable("orderId") Long orderId) {
        return orderHeaderService.getOrder(orderId);
    }

    @PostMapping("/api/v1/order")
    public ResponseEntity addNewOrder(@RequestBody OrderHeader orderHeader) {
        OrderHeader orderHeader1 = orderHeaderService.saveOrder(orderHeader);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/order/" + orderHeader1.getId());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);

    }

    @PutMapping("/api/v1/order/{id}")
    public ResponseEntity putOrder(@RequestBody OrderHeader orderHeader,
                                   @PathVariable("id") Long id) {
        OrderHeader orderHeader1 = orderHeaderService.updateOrder(id, orderHeader);
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Location", "/api/v1/order/" + orderHeader1.getId());
        return new ResponseEntity(httpHeaders, HttpStatus.NO_CONTENT);

    }
}
