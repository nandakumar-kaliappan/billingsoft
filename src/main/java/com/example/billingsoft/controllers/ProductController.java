package com.example.billingsoft.controllers;

import com.example.billingsoft.domain.OrderHeader;
import com.example.billingsoft.domain.OrderLine;
import com.example.billingsoft.domain.Product;
import com.example.billingsoft.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/v1/product")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("api/v1/product")
    public Long addNewProduct() {
        System.out.println("post Request Received for : product ");
        return 50L;
    }

}