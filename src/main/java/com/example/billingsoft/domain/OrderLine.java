package com.example.billingsoft.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString(exclude = "orderHeader", callSuper = true)
@EqualsAndHashCode
public class OrderLine extends BaseEntity{
    private Integer quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JsonBackReference
    private OrderHeader orderHeader;
}
