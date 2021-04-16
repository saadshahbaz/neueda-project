package ca.mcgill.ecse321.arms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Business {
    private String name;

    public void setName(String value) {
        this.name = value;
    }

    @Id
    public String getName() {
        return this.name;
    }

    private String address;

    public void setAddress(String value) {
        this.address = value;
    }

    public String getAddress() {
        return this.address;
    }

    private String phoneNumber;

    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    private String email;

    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return this.email;
    }

    private Set<BusinessHour> businessHour;

    @OneToMany(mappedBy = "business")
    public Set<BusinessHour> getBusinessHour() {
        return this.businessHour;
    }

    /**
     * add a businessHour to business
     * @param businessHour
     */
    public void addBusinessHour(BusinessHour businessHour){
        if (this.businessHour == null) {
            this.businessHour = new HashSet<BusinessHour>();
        }
        this.businessHour.add(businessHour);
        businessHour.setBusiness(this);
    }

    public void removeBusinessHour(BusinessHour businessHour){
        this.businessHour.remove(businessHour);
        businessHour.setBusiness(null);
    }

    public void setBusinessHour(Set<BusinessHour> businessHours) {
        this.businessHour = businessHours;
    }


}