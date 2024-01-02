package com.example.billingsoft.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(exclude = "orderLines")
public class OrderHeader extends BaseEntity {
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "orderHeader",
    cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE,CascadeType.ALL},
            fetch =
            FetchType.EAGER)
    @Builder.Default
    @JsonManagedReference
    private Set<OrderLine> orderLines = new HashSet<>();

    private Integer count;
    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    public void addOrderLine(OrderLine orderLine){
        if(this.orderLines== null){
            this.orderLines = new HashSet<>();
        }
        orderLine.setOrderHeader(this);
        this.orderLines.add(orderLine);

    }
}
