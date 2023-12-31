package com.example.billingsoft.bootstrap;

import com.example.billingsoft.domain.Product;
import com.example.billingsoft.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Bootstrap implements CommandLineRunner {
    private final ProductService productService;

    public Bootstrap(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productService.getAllProducts().size() == 0) {
            loadProductData();
        }
    }

    private void loadProductData() {
        productService.addNewProduct(Product.builder()
                .name("Coconut oil")
                .rate(BigDecimal.valueOf(50))
                .build());
        productService.addNewProduct(Product.builder()
                .name("Sugar")
                .rate(BigDecimal.valueOf(40))
                .build());
        productService.addNewProduct(Product.builder()
                .name("Tooth paste")
                .rate(BigDecimal.valueOf(20))
                .build());
        productService.addNewProduct(Product.builder()
                .name("Candies")
                .rate(BigDecimal.valueOf(10))
                .build());
    }

}
