package com.yasir.microservices.products.controllers;


import com.yasir.microservices.products.dto.ProductRequest;
import com.yasir.microservices.products.dto.ProductResponse;
import com.yasir.microservices.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController{
    @Autowired
    private  ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
           return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
            return  productService.getAllProduct();
    }
}
