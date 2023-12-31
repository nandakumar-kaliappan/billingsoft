package com.example.billingsoft.services;

import com.example.billingsoft.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product addNewProduct(Product build);
}
