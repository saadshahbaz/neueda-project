/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

// line 37 "../../../../../ARMS.ump"
public class Space
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Space Associations
  private List<TimeSlot> timeslot;
  private ARMS aRMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Space(ARMS aARMS)
  {
    timeslot = new ArrayList<TimeSlot>();
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create space due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public TimeSlot getTimeslot(int index)
  {
    TimeSlot aTimeslot = timeslot.get(index);
    return aTimeslot;
  }

  public List<TimeSlot> getTimeslot()
  {
    List<TimeSlot> newTimeslot = Collections.unmodifiableList(timeslot);
    return newTimeslot;
  }

  public int numberOfTimeslot()
  {
    int number = timeslot.size();
    return number;
  }

  public boolean hasTimeslot()
  {
    boolean has = timeslot.size() > 0;
    return has;
  }

  public int indexOfTimeslot(TimeSlot aTimeslot)
  {
    int index = timeslot.indexOf(aTimeslot);
    return index;
  }
  /* Code from template association_GetOne */
  public ARMS getARMS()
  {
    return aRMS;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeslot()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addTimeslot(TimeSlot aTimeslot)
  {
    boolean wasAdded = false;
    if (timeslot.contains(aTimeslot)) { return false; }
    Space existingSpace = aTimeslot.getSpace();
    if (existingSpace == null)
    {
      aTimeslot.setSpace(this);
    }
    else if (!this.equals(existingSpace))
    {
      existingSpace.removeTimeslot(aTimeslot);
      addTimeslot(aTimeslot);
    }
    else
    {
      timeslot.add(aTimeslot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeslot(TimeSlot aTimeslot)
  {
    boolean wasRemoved = false;
    if (timeslot.contains(aTimeslot))
    {
      timeslot.remove(aTimeslot);
      aTimeslot.setSpace(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTimeslotAt(TimeSlot aTimeslot, int index)
  {  
    boolean wasAdded = false;
    if(addTimeslot(aTimeslot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeslot()) { index = numberOfTimeslot() - 1; }
      timeslot.remove(aTimeslot);
      timeslot.add(index, aTimeslot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeslotAt(TimeSlot aTimeslot, int index)
  {
    boolean wasAdded = false;
    if(timeslot.contains(aTimeslot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeslot()) { index = numberOfTimeslot() - 1; }
      timeslot.remove(aTimeslot);
      timeslot.add(index, aTimeslot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeslotAt(aTimeslot, index);
    }
    return wasAdded;
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
      existingARMS.removeSpace(this);
    }
    aRMS.addSpace(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !timeslot.isEmpty() )
    {
      timeslot.get(0).setSpace(null);
    }
    ARMS placeholderARMS = aRMS;
    this.aRMS = null;
    if(placeholderARMS != null)
    {
      placeholderARMS.removeSpace(this);
    }
  }

}