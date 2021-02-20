/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.sql.Time;

import javax.persistence.*;

// line 54 "../../../../../ARMS.ump"
@Entity
public class BusinessHour
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BusinessHour Attributes
  private DayOfWeek dayOfWeek;
  private Time startTime;
  private Time endTime;

  //BusinessHour Associations
  /*private ARMS arms;

  @ManyToOne(optional = false)
  public ARMS getARMS() {
     return this.arms;
  }

  public void setARMS(ARMS aRMS) {
     this.arms = aRMS;
  }*/

  
  private int businessHourID;
  
  public void setBusinessHourID(int value) {
this.businessHourID = value;
   }
@Id
public int getBusinessHourID() {
return this.businessHourID;
      }

  //------------------------
  // CONSTRUCTOR
  //------------------------
/*
  public BusinessHour(DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime, ARMS aARMS)
  {
    dayOfWeek = aDayOfWeek;
    startTime = aStartTime;
    endTime = aEndTime;
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create hour due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }
*/
  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDayOfWeek(DayOfWeek aDayOfWeek)
  {
    boolean wasSet = false;
    dayOfWeek = aDayOfWeek;
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

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public DayOfWeek getDayOfWeek()
  {
    return dayOfWeek;
  }

  public Time getStartTime()
  {
    return startTime;
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
      placeholderARMS.removeHour(this);
    }
  }*/


  /*public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dayOfWeek" + "=" + (getDayOfWeek() != null ? !getDayOfWeek().equals(this)  ? getDayOfWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "aRMS = "+(getARMS()!=null?Integer.toHexString(System.identityHashCode(getARMS())):"null");
  }*/
}