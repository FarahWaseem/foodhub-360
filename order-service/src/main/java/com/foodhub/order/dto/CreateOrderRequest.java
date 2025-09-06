package com.foodhub.order.dto;

import lombok.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {
    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotBlank(message = "Restaurant ID is required")
    private String restaurantId;

    @NotBlank(message = "Branch ID is required")
    private String branchId;

    @NotEmpty(message = "Order must have at least one item")
    @Valid
    private List<OrderItemRequest> items;

    @NotNull(message = "Delivery address is required")
    @Valid
    private DeliveryAddressRequest deliveryAddress;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    private String notes;
}