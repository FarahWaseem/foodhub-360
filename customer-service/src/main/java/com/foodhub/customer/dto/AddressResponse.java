package main.java.com.foodhub.customer.dto;
import main.java.com.foodhub.customer.entity.AddressType;
import main.java.com.foodhub.customer.entity.CustomerAddress;

public class AddressResponse  {
     private String id;
    private AddressType type;
    private String street;
    private String city;
    private String postalCode;
    private Boolean isDefault;
    
    public AddressResponse() {}
    
    public AddressResponse(CustomerAddress address) {
        this.id = address.getId();
        this.type = address.getType();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.postalCode = address.getPostalCode();
        this.isDefault = address.getIsDefault();
    }
    
    // Getters and setters...
    public String getId() { 
        return id; 
    }
    public void setId(String id) { 
        this.id = id; 
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
