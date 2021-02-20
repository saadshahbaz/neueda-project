/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

import javax.persistence.*;

// line 27 "../../../../../ARMS.ump"
@Entity
public class Customer extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private int lastReminder;

  //Customer Associations
  /*private ARMS arms;

  @ManyToOne(optional=false)
  public ARMS getArms() {
     return this.arms;
  }

  public void setArms(ARMS arms) {
     this.arms = arms;
  }*/

  private Set<Bill> bills;
  
  @OneToMany(mappedBy="customer", cascade = {CascadeType.ALL})
  public Set<Bill> getBill() {
     return this.bills;
  }

  public void setBill(Set<Bill> bills) {
     this.bills = bills;
  }
  
  private Set<Car> cars;
  
  @OneToMany(mappedBy="customer", cascade = {CascadeType.ALL})
  public Set<Car> getCar() {
     return this.cars;
  }

  public void setCar(Set<Car> cars) {
     this.cars = cars;
  }
  
  /*private int customerID;

  public void setCustomerID(int value) {
  this.customerID = value;
      }
  @Id
  public int getCustomerID() {
  return this.customerID;
         }*/

  //------------------------
  // CONSTRUCTOR
  //------------------------
/*
  public Customer(String aUsername, String aPassword, ARMS aARMS)
  {
    super(aUsername, aPassword);
    resetLastReminder();
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create customer due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bills = new ArrayList<Bill>();
    cars = new ArrayList<Car>();
  }*/

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetDefaulted */
  public boolean setlastReminder(int aLastReminder)
  {
    boolean wasSet = false;
    lastReminder = aLastReminder;
    wasSet = true;
    return wasSet;
  }

  

  public int getLastReminder()
  {
    return lastReminder;
  }
  
  
  
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCars()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  
  public boolean addCar(Car aCar)
  {
    boolean wasAdded = false;
    if (cars.contains(aCar)) { return false; }
    Customer existingCustomer = aCar.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aCar.setCustomer(this);
    }
    else
    {
      cars.add(aCar);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCar(Car aCar)
  {
    boolean wasRemoved = false;
    //Unable to remove aCar, as it must always have a customer
    if (!this.equals(aCar.getCustomer()))
    {
      cars.remove(aCar);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  

  

  


  /*public String toString()
  {
    return super.toString() + "["+
            "lastReminder" + ":" + getLastReminder()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "aRMS = "+(getArms()!=null?Integer.toHexString(System.identityHashCode(getArms())):"null");
  }*/
}