/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.*;

// line 83 "../../../../../ARMS.ump"
@Entity
public class Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Associations
	private int id;

	public void setId(int value) {
	this.id = value;
	    }
	@Id
	public int getId() {
	return this.id;
	       }
	
    private Car car;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    public Car getCar()
    {
      return car;
    }
    
    private Service services;
    /* Code from template association_GetOne */
    @ManyToOne(cascade = {CascadeType.ALL})
    public Service getServices()
    {
      return services;
    }
    

public void setServices(Service service) {
   this.services = service;
}
    
    private TimeSlot timeSlot;
    @OneToOne(cascade = {CascadeType.ALL})
    public TimeSlot getTimeSlot()
    {
      return timeSlot;
    }
    
    private Set<ARMS> Arms;

    @ManyToMany
    public Set<ARMS> getARMS() {
       return this.Arms;
    }

    public void setARMS(Set<ARMS> Armss) {
       this.Arms = Armss;
    }

  //------------------------
  // CONSTRUCTOR
  //------------------------
/*
  public Appointment(Car aCar, Service aServices, TimeSlot aTimeSlot, ARMS aARMS)
  {
    boolean didAddCar = setCar(aCar);
    if (!didAddCar)
    {
      throw new RuntimeException("Unable to create appointment due to car. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddServices = setServices(aServices);
    if (!didAddServices)
    {
      throw new RuntimeException("Unable to create appointment due to services. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setTimeSlot(aTimeSlot))
    {
      throw new RuntimeException("Unable to create Appointment due to aTimeSlot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create appointment due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }*/

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  
  
  
  
  public void setCar(Car car) {
	   this.car = car;
	}

  
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTimeSlot(TimeSlot aNewTimeSlot)
  {
    boolean wasSet = false;
    if (aNewTimeSlot != null)
    {
      timeSlot = aNewTimeSlot;
      wasSet = true;
    }
    return wasSet;
  }
  

  

}