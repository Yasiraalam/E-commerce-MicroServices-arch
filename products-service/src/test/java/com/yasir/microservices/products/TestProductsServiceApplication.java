package com.yasir.microservices.products;

import org.springframework.boot.SpringApplication;

public class TestProductsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductsServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
