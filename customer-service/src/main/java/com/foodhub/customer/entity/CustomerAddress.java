package main.java.com.foodhub.customer.entity;
import jakarta.persistence.*;
import main.java.com.foodhub.customer.entity.Customer;
import main.java.com.foodhub.customer.entity.AddressType;
import java.util.* ;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "customer_addresses")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    
    // Constructors, Getters and Setters
    public CustomerAddress() {}
    
    public CustomerAddress(Customer customer, AddressType type, String street, String city) {
        this.customer = customer;
        this.type = type;
        this.street = street;
        this.city = city;
    }
    
    // Getters and Setters omitted for brevity...
    public String getId() {
        return id; 
    }
    public void setId(String id) { 
        this.id = id; 
    }
    public Customer getCustomer() { 
        return customer; 
    }
    public void setCustomer(Customer customer) { 
        this.customer = customer; 
    }
    public AddressType getType() { 
        return type; 
    }
    public void setType(AddressType type) { 
        this.type = type; 
    }
    public String getStreet() { 
        return street; 
    }
    public void setStreet(String street) { 
        this.street = street; 
    }
    public String getCity() { 
        return city; 
    }
    public void setCity(String city) { 
        this.city = city; 
    }
    public String getPostalCode() { 
        return postalCode; 
    }
    public void setPostalCode(String postalCode) { 
        this.postalCode = postalCode; 
    }
    public Boolean getIsDefault() { 
        return isDefault; 
    }
    public void setIsDefault(Boolean isDefault) { 
        this.isDefault = isDefault; 
    }





}
