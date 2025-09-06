package main.java.com.foodhub.customer.entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.* ;

@Entity
@Table(name = "customers")
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
    
    // Constructors
    public Customer() {}
    
    public Customer(String name, String email, String phone, String passwordHash) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
    }
    
    // Getters and Setters
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    
    public LocalDateTime getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }
    
    public Integer getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(Integer loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }
    
    public MembershipLevel getMembershipLevel() { return membershipLevel; }
    public void setMembershipLevel(MembershipLevel membershipLevel) { this.membershipLevel = membershipLevel; }
    
    public List<CustomerAddress> getAddresses() { return addresses; }
    public void setAddresses(List<CustomerAddress> addresses) { this.addresses = addresses; }
}