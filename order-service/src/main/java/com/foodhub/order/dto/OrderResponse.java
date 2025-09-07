package com.foodhub.order.dto;

import com.foodhub.order.entity.Order;
import com.foodhub.order.enums.OrderStatus;
import com.foodhub.order.enums.PaymentMethod;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private String orderId;
    private String customerId;
    private String restaurantId;
    private String branchId;
    private List<OrderItemResponse> items;
    private OrderStatus status;
    private DeliveryAddressResponse deliveryAddress;
    private PaymentMethod paymentMethod;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal tax;
    private BigDecimal total;
    private String currency;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime createdAt;
    private List<OrderStatusChangeResponse> timeline;
    private String notes;

    public OrderResponse(Order order) {
        this.orderId = order.getOrderId();
        this.customerId = order.getCustomerId();
        this.restaurantId = order.getRestaurantId();
        this.branchId = order.getBranchId();
        this.items = order.getItems().stream().map(OrderItemResponse::new).collect(Collectors.toList());
        this.status = order.getStatus();
        this.deliveryAddress = new DeliveryAddressResponse(order.getDeliveryAddress());
        this.paymentMethod = order.getPaymentMethod();
        this.subtotal = order.getSubtotal();
        this.deliveryFee = order.getDeliveryFee();
        this.tax = order.getTax();
        this.total = order.getTotal();
        this.currency = order.getCurrency();
        this.estimatedDeliveryTime = order.getEstimatedDeliveryTime();
        this.createdAt = order.getCreatedAt();
        this.timeline = order.getTimeline().stream().map(OrderStatusChangeResponse::new).collect(Collectors.toList());
        this.notes = order.getNotes();
    }
}