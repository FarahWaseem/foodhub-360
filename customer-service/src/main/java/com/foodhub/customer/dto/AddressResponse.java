package com.foodhub.customer.dto;

import lombok.*;
import com.foodhub.customer.entity.CustomerAddress;
import com.foodhub.customer.enums.AddressType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {
    private String id;
    private AddressType type;
    private String street;
    private String city;
    private String postalCode;
    private Boolean isDefault;

    public AddressResponse(CustomerAddress address) {
        this.id = address.getId();
        this.type = address.getType();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.postalCode = address.getPostalCode();
        this.isDefault = address.getIsDefault();
    }
}