package main.java.com.foodhub.customer.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.java.com.foodhub.customer.entity.Customer;
import main.java.com.foodhub.customer.entity.MembershipLevel;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Customer> findByMembershipLevel(MembershipLevel level);
}