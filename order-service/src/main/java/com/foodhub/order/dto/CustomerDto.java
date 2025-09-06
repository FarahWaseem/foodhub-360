package com.foodhub.order.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private Integer loyaltyPoints;
    private String membershipLevel;
}