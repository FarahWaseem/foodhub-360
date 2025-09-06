package com.foodhub.order.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAddress {
    private String street;
    private String city;

    @Field("postal_code")
    private String postalCode;

    private String phone;
}