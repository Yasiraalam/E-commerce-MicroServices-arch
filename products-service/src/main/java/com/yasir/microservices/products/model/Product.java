package com.yasir.microservices.products.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(value = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product{
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> categories;
    private BigDecimal price;
}
