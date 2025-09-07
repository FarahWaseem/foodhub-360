package com.foodhub.order.dto;
import com.foodhub.order.enums.OrderStatus;

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