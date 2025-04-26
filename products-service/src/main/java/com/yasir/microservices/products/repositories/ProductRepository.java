package com.yasir.microservices.products.repositories;

import com.yasir.microservices.products.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
