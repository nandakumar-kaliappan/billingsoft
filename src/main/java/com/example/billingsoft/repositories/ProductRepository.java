package com.example.billingsoft.repositories;

import com.example.billingsoft.domain.Product;
import org.springframework.boot.autoconfigure.batch.JpaBatchConfigurer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
