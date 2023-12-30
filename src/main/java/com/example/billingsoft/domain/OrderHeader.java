package com.example.billingsoft.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderHeader extends BaseEntity {
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "orderHeader",
    cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<OrderLine> orderLines;

    private Integer count;
    @Column(precision = 10, scale = 2)
    private BigDecimal amount;
}
