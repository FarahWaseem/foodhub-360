package com.foodhub.order.entity;

import com.foodhub.order.enums.OrderStatus;
import com.foodhub.order.enums.PaymentMethod;


import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    private String orderId;

    @Field("customer_id")
    private String customerId;

    @Field("restaurant_id")
    private String restaurantId;

    @Field("branch_id")
    private String branchId;

        @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

        @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;

    @Field("delivery_address")
    private DeliveryAddress deliveryAddress;

    @Field("payment_method")
    private PaymentMethod paymentMethod;

    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal tax;
    private BigDecimal total;
    @Builder.Default
    private String currency = "ILS";

    @Field("estimated_delivery_time")
    private LocalDateTime estimatedDeliveryTime;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<OrderStatusChange> timeline = new ArrayList<>();

    private String notes;

    public Order(String customerId, String restaurantId, String branchId) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.branchId = branchId;
        this.timeline.add(new OrderStatusChange(OrderStatus.PENDING, LocalDateTime.now(), "Order received"));
    }
}