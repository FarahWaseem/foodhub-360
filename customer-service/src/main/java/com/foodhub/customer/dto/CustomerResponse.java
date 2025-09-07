package com.foodhub.customer.dto;

import lombok.*;
import com.foodhub.customer.entity.Customer;
import com.foodhub.customer.enums.MembershipLevel;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime registrationDate;
    private Integer loyaltyPoints;
    private MembershipLevel membershipLevel;
    private List<AddressResponse> addresses;

    public CustomerResponse(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.registrationDate = customer.getRegistrationDate();
        this.loyaltyPoints = customer.getLoyaltyPoints();
        this.membershipLevel = customer.getMembershipLevel();
        this.addresses = customer.getAddresses().stream().map(AddressResponse::new).collect(Collectors.toList());
    }
}