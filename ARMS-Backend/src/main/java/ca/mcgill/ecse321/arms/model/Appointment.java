/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.sql.Date;
import java.sql.Time;

// line 83 "../../../../../ARMS.ump"
public class Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Associations
  private Car car;
  private Service services;
  private TimeSlot timeSlot;
  private ARMS aRMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

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
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Car getCar()
  {
    return car;
  }
  /* Code from template association_GetOne */
  public Service getServices()
  {
    return services;
  }
  /* Code from template association_GetOne */
  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }
  /* Code from template association_GetOne */
  public ARMS getARMS()
  {
    return aRMS;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCar(Car aCar)
  {
    boolean wasSet = false;
    if (aCar == null)
    {
      return wasSet;
    }

    Car existingCar = car;
    car = aCar;
    if (existingCar != null && !existingCar.equals(aCar))
    {
      existingCar.removeAppointment(this);
    }
    car.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setServices(Service aServices)
  {
    boolean wasSet = false;
    if (aServices == null)
    {
      return wasSet;
    }

    Service existingServices = services;
    services = aServices;
    if (existingServices != null && !existingServices.equals(aServices))
    {
      existingServices.removeAppointment(this);
    }
    services.addAppointment(this);
    wasSet = true;
    return wasSet;
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
  /* Code from template association_SetOneToMany */
  public boolean setARMS(ARMS aARMS)
  {
    boolean wasSet = false;
    if (aARMS == null)
    {
      return wasSet;
    }

    ARMS existingARMS = aRMS;
    aRMS = aARMS;
    if (existingARMS != null && !existingARMS.equals(aARMS))
    {
      existingARMS.removeAppointment(this);
    }
    aRMS.addAppointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Car placeholderCar = car;
    this.car = null;
    if(placeholderCar != null)
    {
      placeholderCar.removeAppointment(this);
    }
    Service placeholderServices = services;
    this.services = null;
    if(placeholderServices != null)
    {
      placeholderServices.removeAppointment(this);
    }
    timeSlot = null;
    ARMS placeholderARMS = aRMS;
    this.aRMS = null;
    if(placeholderARMS != null)
    {
      placeholderARMS.removeAppointment(this);
    }
  }

}