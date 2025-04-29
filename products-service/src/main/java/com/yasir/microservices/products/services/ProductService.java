package com.yasir.microservices.products.services;
import com.yasir.microservices.products.dto.ProductRequest;
import com.yasir.microservices.products.dto.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest, String imageUrl);
    List<ProductResponse> getAllProduct();
    ProductResponse getProductById(String id);
    ProductResponse updateProduct(String id, ProductRequest productRequest);
    Boolean deleteProduct(String id);
    List<ProductResponse> searchProductsByName(String query);
}