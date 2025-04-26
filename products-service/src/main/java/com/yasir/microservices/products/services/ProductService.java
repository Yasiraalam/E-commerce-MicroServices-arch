package com.yasir.microservices.products.services;


import com.yasir.microservices.products.dto.ProductRequest;
import com.yasir.microservices.products.dto.ProductResponse;
import com.yasir.microservices.products.model.Product;
import com.yasir.microservices.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private  ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created successfully!");
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream().map(product -> new ProductResponse(product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()))
                .toList();

    }
}
