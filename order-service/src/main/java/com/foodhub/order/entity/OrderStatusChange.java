package com.foodhub.order.entity;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusChange {
    private OrderStatus status;
    private LocalDateTime timestamp;
    private String note;
}