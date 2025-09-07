package com.foodhub.customer.repository;

import com.foodhub.customer.entity.Customer;
import com.foodhub.customer.enums.MembershipLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Customer> findByMembershipLevel(MembershipLevel level);
}