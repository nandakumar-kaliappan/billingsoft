package com.example.billingsoft.services;

import com.example.billingsoft.domain.Product;
import com.example.billingsoft.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product addNewProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(productRepository.
                findById(id)
                .orElseThrow(()->new IllegalArgumentException()));
    }
}
