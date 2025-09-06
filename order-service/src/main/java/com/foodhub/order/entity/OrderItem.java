package com.foodhub.order.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Field("item_id")
    private String itemId;

    private String name;
    private Integer quantity;

    @Field("unit_price")
    private BigDecimal unitPrice;

    @Field("total_price")
    private BigDecimal totalPrice;

    @Field("special_instructions")
    private String specialInstructions;

    public OrderItem(String itemId, String name, Integer quantity, BigDecimal unitPrice) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}