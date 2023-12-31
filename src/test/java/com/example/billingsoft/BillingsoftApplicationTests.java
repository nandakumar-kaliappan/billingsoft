package com.example.billingsoft;

import com.example.billingsoft.domain.Customer;
import com.example.billingsoft.domain.OrderHeader;
import com.example.billingsoft.domain.Product;
import com.example.billingsoft.services.OrderHeaderService;
import com.example.billingsoft.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BillingsoftApplicationTests {
    @Autowired
    ProductService productService;
    @Autowired
    OrderHeaderService orderHeaderService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    //    Order
    @Test
    @Transactional
    void getAllOrders() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    void addNewOrder() throws Exception {
        OrderHeader testOrderHeader = OrderHeader.builder()
                .amount(BigDecimal.valueOf(10))
                .count(0)
                .customer(Customer.builder().name("ram").build())
                .build();
        List<OrderHeader> allOrdersInit = orderHeaderService.getAllOrders();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testOrderHeader)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        List<OrderHeader> allOrdersAft = orderHeaderService.getAllOrders();
        assertThat(allOrdersAft.size()).isEqualTo(allOrdersInit.size()+1);
    }


    //    Product
    @Test
    @Transactional
    void addNewProduct() {
        int sizeBefore = productService.getAllProducts().size();
        Product coconutOil = productService.addNewProduct(Product.builder()
                .name("Coconut oil")
                .rate(BigDecimal.valueOf(50))
                .build());
        int sizeAfter = productService.getAllProducts().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore + 1);
        assertNotNull(coconutOil.getId());
        System.out.println("sizeAfter " + sizeAfter);
    }

    @Test
    @Transactional
    void getAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)));
    }

}
