/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

import javax.persistence.*;

// line 75 "../../../../../ARMS.ump"
@Entity
public class Car
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Car Attributes
  private String model;
  private String manufacturer;
  private String plateNo;
  private String year;

  //Car Associations
  private Customer customer;
  private ARMS arms;

  @ManyToOne(optional=false)
  public ARMS getArms() {
     return this.arms;
  }

  public void setArms(ARMS arms) {
     this.arms = arms;
  }
  private Set<Appointment> appointments;
  
  @OneToMany(mappedBy="car")
  public Set<Appointment> getAppointment() {
     return this.appointments;
  }

  public void setAppointment(Set<Appointment> appointments) {
     this.appointments = appointments;
  }
  private int carNo;

  public void setCarNo(int value) {
  this.carNo = value;
      }
  @Id
  public int getCarNo() {
  return this.carNo;}

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /*public Car(String aModel, String aManufacturer, String aPlateNo, String aYear, Customer aCustomer, ARMS aARMS)
  {
    model = aModel;
    manufacturer = aManufacturer;
    plateNo = aPlateNo;
    year = aYear;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create car due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create car due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointments = new Set<Appointment>();
  }*/

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setModel(String aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public boolean setManufacturer(String aManufacturer)
  {
    boolean wasSet = false;
    manufacturer = aManufacturer;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlateNo(String aPlateNo)
  {
    boolean wasSet = false;
    plateNo = aPlateNo;
    wasSet = true;
    return wasSet;
  }

  public boolean setYear(String aYear)
  {
    boolean wasSet = false;
    year = aYear;
    wasSet = true;
    return wasSet;
  }

  public String getModel()
  {
    return model;
  }

  public String getManufacturer()
  {
    return manufacturer;
  }

  public String getPlateNo()
  {
    return plateNo;
  }

  public String getYear()
  {
    return year;
  }
  /* Code from template association_GetOne */
  @ManyToOne(cascade = {CascadeType.ALL})
  public Customer getCustomer()
  {
    return customer;
  }
  
 

  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeCar(this);
    }
    customer.addCar(this);
    wasSet = true;
    return wasSet;
  }
  
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  
  /* Code from template association_AddIndexControlFunctions */
  

  
  


  public String toString()
  {
    return super.toString() + "["+
            "model" + ":" + getModel()+ "," +
            "manufacturer" + ":" + getManufacturer()+ "," +
            "plateNo" + ":" + getPlateNo()+ "," +
            "year" + ":" + getYear()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "aRMS = "+(getArms()!=null?Integer.toHexString(System.identityHashCode(getArms())):"null");
  }
}