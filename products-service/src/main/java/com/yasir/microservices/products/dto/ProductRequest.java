package com.yasir.microservices.products.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        String id,
        String name,
        String description,
        List<String> categories,
        BigDecimal price
) {

}
