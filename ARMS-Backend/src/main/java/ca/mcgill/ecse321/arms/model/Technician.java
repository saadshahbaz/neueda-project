/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

// line 32 "../../../../../ARMS.ump"
public class Technician
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Technician Attributes
  private String name;

  //Technician Associations
  private List<TimeSlot> work;
  private ARMS aRMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Technician(String aName, ARMS aARMS)
  {
    name = aName;
    work = new ArrayList<TimeSlot>();
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create technician due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

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

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public TimeSlot getWork(int index)
  {
    TimeSlot aWork = work.get(index);
    return aWork;
  }

  public List<TimeSlot> getWork()
  {
    List<TimeSlot> newWork = Collections.unmodifiableList(work);
    return newWork;
  }

  public int numberOfWork()
  {
    int number = work.size();
    return number;
  }

  public boolean hasWork()
  {
    boolean has = work.size() > 0;
    return has;
  }

  public int indexOfWork(TimeSlot aWork)
  {
    int index = work.indexOf(aWork);
    return index;
  }
  /* Code from template association_GetOne */
  public ARMS getARMS()
  {
    return aRMS;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWork()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addWork(TimeSlot aWork)
  {
    boolean wasAdded = false;
    if (work.contains(aWork)) { return false; }
    Technician existingTechnician = aWork.getTechnician();
    if (existingTechnician == null)
    {
      aWork.setTechnician(this);
    }
    else if (!this.equals(existingTechnician))
    {
      existingTechnician.removeWork(aWork);
      addWork(aWork);
    }
    else
    {
      work.add(aWork);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWork(TimeSlot aWork)
  {
    boolean wasRemoved = false;
    if (work.contains(aWork))
    {
      work.remove(aWork);
      aWork.setTechnician(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWorkAt(TimeSlot aWork, int index)
  {  
    boolean wasAdded = false;
    if(addWork(aWork))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWork()) { index = numberOfWork() - 1; }
      work.remove(aWork);
      work.add(index, aWork);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWorkAt(TimeSlot aWork, int index)
  {
    boolean wasAdded = false;
    if(work.contains(aWork))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWork()) { index = numberOfWork() - 1; }
      work.remove(aWork);
      work.add(index, aWork);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWorkAt(aWork, index);
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
      existingARMS.removeTechnician(this);
    }
    aRMS.addTechnician(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !work.isEmpty() )
    {
      work.get(0).setTechnician(null);
    }
    ARMS placeholderARMS = aRMS;
    this.aRMS = null;
    if(placeholderARMS != null)
    {
      placeholderARMS.removeTechnician(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "aRMS = "+(getARMS()!=null?Integer.toHexString(System.identityHashCode(getARMS())):"null");
  }
}