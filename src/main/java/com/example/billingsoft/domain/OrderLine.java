package com.example.billingsoft.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OrderLine extends BaseEntity{
    private Integer quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    private OrderHeader orderHeader;
}
