package com.yasir.microservices.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Table(name = "t_orders")
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
