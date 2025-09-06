package com.foodhub.order.repository;

import com.foodhub.order.entity.Order;
import com.foodhub.order.enums.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByCustomerId(String customerId);
    List<Order> findByRestaurantId(String restaurantId);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCustomerIdAndStatus(String customerId, OrderStatus status);
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}