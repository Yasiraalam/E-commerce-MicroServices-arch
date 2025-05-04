# 🧭 **👉 You can explore individual service code by clicking the 🔀 `branch` icon above – each branch contains one of the services (Product, Order, Inventory).**

# 🛒 Spring Boot Microservices – Product, Order & Inventory

This project demonstrates a modular and scalable **Spring Boot-based microservices architecture** simulating a basic e-commerce system. It includes **three core services** working together to manage products, handle orders, and check inventory — all routed through a secured API Gateway.

## 📦 Microservices Overview

### 1. 🛍️ Product Service
Manages product information including:
- Adding new products
- Updating product details
- Listing available products
- Uses **MongoDB** as the database, managed via **Docker**

### 2. 📦 Inventory Service
Handles stock management:
- Checks inventory status for requested products
- Updates stock on successful order placement
- Uses **MySQL** as the database

### 3. 📑 Order Service
Processes customer orders:
- Receives order requests from the client
- Validates product availability via Inventory Service
- Confirms and stores the order if inventory is sufficient
- Uses **MySQL** as the database
- Uses **Resilience4j** for **circuit breaker** pattern to ensure fault tolerance

## 🛡️ API Gateway

- Central entry point for all client interactions
- Built using **Spring Cloud Gateway**
- Secured using **OAuth2** authentication
- Routes requests to the appropriate microservices
- Uses **Spring Cloud OpenFeign** to simplify and abstract service-to-service communication

## 🔁 How It Works

1. **Client sends a POST request** to place an order.
2. The request first goes through the **API Gateway**.
3. The **Order Service** receives the request, then performs a nested REST call to the **Inventory Service** using **OpenFeign**.
4. If inventory is available, the order is confirmed and saved.
5. All services are registered with **Eureka** and communicate internally using **OpenFeign Clients**.



