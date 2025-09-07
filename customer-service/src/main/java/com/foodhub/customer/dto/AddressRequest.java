package com.foodhub.customer.dto;

import lombok.*;
import com.foodhub.customer.enums.AddressType;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest {
    @NotNull(message = "Address type is required")
    private AddressType type;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    private String postalCode;
}