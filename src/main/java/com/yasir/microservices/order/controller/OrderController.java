package com.yasir.microservices.order.controller;


import com.yasir.microservices.order.Dto.OrderRequest;
import com.yasir.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String PlaceOrder(@RequestBody OrderRequest orderRequest) {
        orderService.PlaceOrder(orderRequest);
        return "Order Placed Successfully";
    }
}
