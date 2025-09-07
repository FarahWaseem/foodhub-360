package com.foodhub.customer.entity;

import lombok.*;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.foodhub.customer.enums.AddressType;

@Entity
@Table(name = "customer_addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddress {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private AddressType type;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    private String postalCode;

    private Boolean isDefault = false;

    public CustomerAddress(Customer customer, AddressType type, String street, String city) {
        this.customer = customer;
        this.type = type;
        this.street = street;
        this.city = city;
    }
}