/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.sql.Date;
import java.sql.Time;

// line 61 "../../../../../ARMS.ump"
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
  private ARMS aRMS;
  private Technician technician;
  private Space space;

  //------------------------
  // CONSTRUCTOR
  //------------------------

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
  /* Code from template association_GetOne */
  public ARMS getARMS()
  {
    return aRMS;
  }
  /* Code from template association_GetOne */
  public Technician getTechnician()
  {
    return technician;
  }

  public boolean hasTechnician()
  {
    boolean has = technician != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Space getSpace()
  {
    return space;
  }

  public boolean hasSpace()
  {
    boolean has = space != null;
    return has;
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
      existingARMS.removeTimeSlot(this);
    }
    aRMS.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setTechnician(Technician aTechnician)
  {
    boolean wasSet = false;
    Technician existingTechnician = technician;
    technician = aTechnician;
    if (existingTechnician != null && !existingTechnician.equals(aTechnician))
    {
      existingTechnician.removeWork(this);
    }
    if (aTechnician != null)
    {
      aTechnician.addWork(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setSpace(Space aSpace)
  {
    boolean wasSet = false;
    Space existingSpace = space;
    space = aSpace;
    if (existingSpace != null && !existingSpace.equals(aSpace))
    {
      existingSpace.removeTimeslot(this);
    }
    if (aSpace != null)
    {
      aSpace.addTimeslot(this);
    }
    wasSet = true;
    return wasSet;
  }

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


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "aRMS = "+(getARMS()!=null?Integer.toHexString(System.identityHashCode(getARMS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "technician = "+(getTechnician()!=null?Integer.toHexString(System.identityHashCode(getTechnician())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "space = "+(getSpace()!=null?Integer.toHexString(System.identityHashCode(getSpace())):"null");
  }
}