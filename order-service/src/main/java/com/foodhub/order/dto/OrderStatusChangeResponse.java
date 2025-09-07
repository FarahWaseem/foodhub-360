package com.foodhub.order.dto;

import lombok.*;
import java.time.LocalDateTime;
import com.foodhub.order.entity.OrderStatusChange;
import com.foodhub.order.enums.OrderStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusChangeResponse {
    private OrderStatus status;
    private LocalDateTime timestamp;
    private String note;

    public OrderStatusChangeResponse(OrderStatusChange change) {
        this.status = change.getStatus();
        this.timestamp = change.getTimestamp();
        this.note = change.getNote();
    }
}