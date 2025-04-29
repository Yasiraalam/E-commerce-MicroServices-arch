package com.yasir.microservices.products.dto;

import java.math.BigDecimal;

public record ProductResponse(String id, String name, String description,String imageUrl, BigDecimal price) {

}
