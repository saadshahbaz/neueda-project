/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Business{
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

@OneToMany
public Set<BusinessHour> getBusinessHour() {
   return this.businessHour;
}

public void setBusinessHour(Set<BusinessHour> businessHours) {
   this.businessHour = businessHours;
}

private ARMS ARMS;

@OneToOne(optional=false)
public ARMS getARMS() {
   return this.ARMS;
}

public void setARMS(ARMS aRMS) {
   this.ARMS = aRMS;
}

}