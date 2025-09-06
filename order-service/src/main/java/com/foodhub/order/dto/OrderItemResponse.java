package com.foodhub.order.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {
    private String itemId;
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String specialInstructions;

    public OrderItemResponse(com.foodhub.order.entity.OrderItem item) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.quantity = item.getQuantity();
        this.unitPrice = item.getUnitPrice();
        this.totalPrice = item.getTotalPrice();
        this.specialInstructions = item.getSpecialInstructions();
    }
}