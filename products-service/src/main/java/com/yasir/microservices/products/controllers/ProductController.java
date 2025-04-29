package com.yasir.microservices.products.controllers;


import com.yasir.microservices.products.dto.ProductRequest;
import com.yasir.microservices.products.dto.ProductResponse;
import com.yasir.microservices.products.services.impl.ProductServiceImpl;
import com.yasir.microservices.products.services.impl.S3ImageUploader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.rmi.server.LogStream.log;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController{
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private S3ImageUploader s3ImageUploader;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(
            @RequestPart("product") String productRequest,
            @RequestPart("image") MultipartFile image
        ){
        try {
            ProductRequest productRequest1 = new ObjectMapper().readValue(productRequest, ProductRequest.class);
            String uploadedFileName = s3ImageUploader.fileUploadImage(image);
            return productService.createProduct(productRequest1, uploadedFileName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse product JSON or upload image", e);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
            return  productService.getAllProduct();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable("id") String id) {
        return productService.getProductById(id);
    }

    // 🚀 Update a product
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProduct(@PathVariable("id") String id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }

    // 🚀 Delete a product
    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable("id") String id) {
        return productService.deleteProduct(id);
    }

    // 🚀 Search products by name
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> searchProducts(@RequestParam("query") String query) {
        return productService.searchProductsByName(query);
    }
}
