/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

// line 61 "../../../../../ARMS.ump"
@Entity
public class TimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private Date startDate;
  private Time startTime;
  private Date endDate;
  private Time endTime;

  //TimeSlot Associations
  private ARMS arms;

@ManyToOne(optional=false)
public ARMS getArms() {
   return this.arms;
}

public void setArms(ARMS arms) {
   this.arms = arms;
}

  private Technician technician;

  @ManyToOne(cascade = {CascadeType.ALL})
  public Technician getTechnician() {
     return this.technician;
  }

  public void setTechnician(Technician technician) {
     this.technician = technician;
  }
  private Space space;

  @ManyToOne(cascade = {CascadeType.ALL})
  public Space getSpace() {
     return this.space;
  }

  public void setSpace(Space space) {
     this.space = space;
  }
  
  private int timeSlotID;

  public void setTimeSlotID(int value) {
  this.timeSlotID = value;
      }
  @Id
  public int getTimeSlotID() {
  return this.timeSlotID;
         }
  //------------------------
  // CONSTRUCTOR
  //------------------------
/*
  public TimeSlot(Date aStartDate, Time aStartTime, Date aEndDate, Time aEndTime, ARMS aARMS)
  {
    startDate = aStartDate;
    startTime = aStartTime;
    endDate = aEndDate;
    endTime = aEndTime;
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create timeSlot due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }
*/
  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public Time getEndTime()
  {
    return endTime;
  }
  
  /*
  public void delete()
  {
    ARMS placeholderARMS = aRMS;
    this.aRMS = null;
    if(placeholderARMS != null)
    {
      placeholderARMS.removeTimeSlot(this);
    }
    if (technician != null)
    {
      Technician placeholderTechnician = technician;
      this.technician = null;
      placeholderTechnician.removeWork(this);
    }
    if (space != null)
    {
      Space placeholderSpace = space;
      this.space = null;
      placeholderSpace.removeTimeslot(this);
    }
  }
*/

  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "aRMS = "+(getArms()!=null?Integer.toHexString(System.identityHashCode(getArms())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "technician = "+(getTechnician()!=null?Integer.toHexString(System.identityHashCode(getTechnician())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "space = "+(getSpace()!=null?Integer.toHexString(System.identityHashCode(getSpace())):"null");
  }
}