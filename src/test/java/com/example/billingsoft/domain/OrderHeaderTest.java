package com.example.billingsoft.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderHeaderTest {

    @Test
    void addOrderLine() {
        OrderHeader testOrderHeader = OrderHeader.builder()
                .amount(BigDecimal.valueOf(10))
                .count(0)
                .customer(Customer.builder().name("ram").build())
                .build();
        Product sampoo = Product.builder()
                .name("Sampoo")
                .rate(BigDecimal.valueOf(20))
                .build();

        Product soap = Product.builder()
                .name("soap")
                .rate(BigDecimal.valueOf(40))
                .build();
        assertFalse(soap.equals(sampoo));
        testOrderHeader.addOrderLine(OrderLine.builder().product(sampoo)
                .build());

        testOrderHeader.addOrderLine(OrderLine.builder().product(soap)
                .build());
        assertThat(testOrderHeader.getOrderLines().size()).isEqualTo(2);
    }
}