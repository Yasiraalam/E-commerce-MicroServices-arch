package com.yasir.microservices.order.service;

import com.yasir.microservices.order.Dto.OrderRequest;
import com.yasir.microservices.order.Repository.OrderRepository;
import com.yasir.microservices.order.client.InventoryClient;
import com.yasir.microservices.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void PlaceOrder(OrderRequest orderRequest) {
        boolean ProductIsInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if(ProductIsInStock){
            //map orderRequest to order object
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            //save order to OrderRepository
            orderRepository.save(order);
        }else{
            throw new RuntimeException("Product with skuCode "+ orderRequest.skuCode()+ " is not in Stock!");
        }


    }
}
