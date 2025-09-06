package com.foodhub.customer.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String customerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String passwordHash;

    @CreationTimestamp
    private LocalDateTime registrationDate;

    private Integer loyaltyPoints = 0;

    @Enumerated(EnumType.STRING)
    private MembershipLevel membershipLevel = MembershipLevel.BRONZE;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerAddress> addresses = new ArrayList<>();

    public Customer(String name, String email, String phone, String passwordHash) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
    }
}