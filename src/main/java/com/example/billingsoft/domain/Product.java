package com.example.billingsoft.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString(callSuper = true)
public class Product extends BaseEntity {
    private String name;
    @Column(precision = 10, scale=2)
    private BigDecimal rate;
}
