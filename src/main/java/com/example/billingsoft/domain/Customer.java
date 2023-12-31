package com.example.billingsoft.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Customer extends  BaseEntity{
    private String name;
    private String address;
    private String phone;
}
