/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

import javax.persistence.*;

// line 46 "../../../../../ARMS.ump"
@Entity
public class Business
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Business Attributes
  private String name;
  private String address;
  private String phoneNumber;
  private String email;

  private int businessID;

  public void setBusinessID(int value) {
  this.businessID = value;
      }
  @Id
  public int getBusinessID() {
  return this.businessID;
         }
  //Business Associations
  private Set<BusinessHour> businessHour;

  @OneToMany
  public Set<BusinessHour> getBussinessHour() {
     return this.businessHour;
  }
  
  public void setBussinessHour(Set<BusinessHour> bussinessHours) {
	   this.businessHour = bussinessHours;
	}
  
  
  private ARMS arms;

  @OneToOne(optional=false)
  public ARMS getArms() {
     return this.arms;
  }

  public void setArms(ARMS arms) {
     this.arms = arms;
  }


  //------------------------
  // CONSTRUCTOR
  //------------------------

  /*public Business(String aName, String aAddress, String aPhoneNumber, String aEmail, ARMS aARMS)
  {
    name = aName;
    address = aAddress;
    phoneNumber = aPhoneNumber;
    email = aEmail;
    businessHours = new ArrayList<BusinessHour>();
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create business due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }*/

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmail()
  {
    return email;
  }
  

  
  
  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "email" + ":" + getEmail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "aRMS = "+(getArms()!=null?Integer.toHexString(System.identityHashCode(getArms())):"null");
  }
}