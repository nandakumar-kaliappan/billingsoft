package com.example.billingsoft.controllers;

import com.example.billingsoft.domain.OrderHeader;
import com.example.billingsoft.domain.OrderLine;
import com.example.billingsoft.domain.Product;
import com.example.billingsoft.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("api/v1/product/{id}")
    public Product getAProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/api/v1/product")
    public ResponseEntity addNewProduct(@RequestBody Product product) {
        Product newProduct = productService.addNewProduct(product);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/product/" + newProduct.getId());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/v1/product/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity(httpHeaders, HttpStatus.NO_CONTENT);
    }

}
