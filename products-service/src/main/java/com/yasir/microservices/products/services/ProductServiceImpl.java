package com.yasir.microservices.products.services;

import com.yasir.microservices.products.dto.ProductRequest;
import com.yasir.microservices.products.dto.ProductResponse;
import com.yasir.microservices.products.model.Product;
import com.yasir.microservices.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .categories(productRequest.categories())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created successfully with id {}", product.getId());
        return mapToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapToProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(productRequest.name());
        existingProduct.setDescription(productRequest.description());
        existingProduct.setPrice(productRequest.price());

        productRepository.save(existingProduct);
        log.info("Product updated successfully with id {}", id);
        return mapToProductResponse(existingProduct);
    }

    @Override
    public Boolean deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        log.info("Product deleted successfully with id {}", id);
        return true;
    }

    @Override
    public List<ProductResponse> searchProductsByName(String query) {
        return productRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    // Private helper method
    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}