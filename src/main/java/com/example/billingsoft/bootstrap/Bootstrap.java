package com.example.billingsoft.bootstrap;

import com.example.billingsoft.domain.Customer;
import com.example.billingsoft.domain.Product;
import com.example.billingsoft.services.CustomerService;
import com.example.billingsoft.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Bootstrap implements CommandLineRunner {
    private final ProductService productService;
    private final CustomerService customerService;

    public Bootstrap(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productService.getAllProducts().size() == 0) {
            loadProductData();
        }
        if (customerService.getAllCustomers().size() == 0) {
            loadCustomerData();
        }
    }

    private void loadCustomerData() {
        customerService.addNewCustomer(Customer.builder()
                .name("Rajkumar")
                .address("Chennai")
                .phone("9600700158")
                .build());
        customerService.addNewCustomer(Customer.builder()
                .name("Sathish")
                .address("Tiruppur")
                .phone("7600704102")
                .build());
        customerService.addNewCustomer(Customer.builder()
                .name("Anbu")
                .address("Sathyamangalam")
                .phone("9120700178")
                .build());
        customerService.addNewCustomer(Customer.builder()
                .name("Balakumar")
                .address("Erode")
                .phone("6600590150")
                .build());
        customerService.addNewCustomer(Customer.builder()
                .name("Vinayagam")
                .address("Kovai")
                .phone("9100333100")
                .build());
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
