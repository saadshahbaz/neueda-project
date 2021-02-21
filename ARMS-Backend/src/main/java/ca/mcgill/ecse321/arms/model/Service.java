/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

import javax.persistence.*;

// line 69 "../../../../../ARMS.ump"
@Entity
public class Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  
  private String name;
  
  public void setName(String value) {
this.name = value;
   }
@Id
public String getName() {
return this.name;
   }
  private int duration;
  private int price;

  //Service Associations
  private ARMS arms;

  @ManyToOne(cascade = {CascadeType.ALL})
  public ARMS getArms() {
     return this.arms;
  }

  public void setArms(ARMS arms) {
     this.arms = arms;
  }
  private Set<Appointment> appointment;

  @OneToMany(mappedBy="services",cascade = {CascadeType.ALL})
  public Set<Appointment> getAppointment() {
     return this.appointment;
  }

  public void setAppointment(Set<Appointment> appointments) {
     this.appointment = appointments;
  }

  //------------------------
  // CONSTRUCTOR
  //------------------------
/*
  public Service(String aName, int aDuration, int aPrice, ARMS aARMS)
  {
    name = aName;
    duration = aDuration;
    price = aPrice;
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create service due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointments = new ArrayList<Appointment>();
  }*/

  //------------------------
  // INTERFACE
  //------------------------

  

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  

  public int getDuration()
  {
    return duration;
  }

  public int getPrice()
  {
    return price;
  }
 
  
  
  


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "duration" + ":" + getDuration()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "aRMS = "+(getArms()!=null?Integer.toHexString(System.identityHashCode(getArms())):"null");
  }
}