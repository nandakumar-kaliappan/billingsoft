package com.example.billingsoft;

import com.example.billingsoft.domain.Product;
import com.example.billingsoft.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BillingsoftApplicationTests {
    @Autowired
    ProductService productService;

    @Test
    @Transactional
    void addNewProduct() {
        int sizeBefore = productService.getAllProducts().size();
        Product coconutOil = productService.addNewProduct(Product.builder()
                .name("Coconut oil")
                .rate(BigDecimal.valueOf(50))
                .build());
        int sizeAfter = productService.getAllProducts().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore+1);
        assertNotNull(coconutOil.getId());
        System.out.println("sizeAfter "+ sizeAfter);
    }

}
