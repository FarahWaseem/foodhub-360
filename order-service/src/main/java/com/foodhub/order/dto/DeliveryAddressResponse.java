package com.foodhub.order.dto;

import lombok.*;
import com.foodhub.order.entity.DeliveryAddress;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAddressResponse {
    private String street;
    private String city;
    private String postalCode;
    private String phone;

    public DeliveryAddressResponse(DeliveryAddress address) {
        this.street = address.getStreet();
        this.city = address.getCity();
        this.postalCode = address.getPostalCode();
        this.phone = address.getPhone();
    }
}