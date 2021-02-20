/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

import javax.persistence.*;

// line 32 "../../../../../ARMS.ump"
@Entity
public class Technician
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Technician Attributes
  private String name;

  //Technician Associations
  private Set<TimeSlot> timeSlot;

  @OneToMany(mappedBy="technician",cascade = {CascadeType.ALL})
  public Set<TimeSlot> getTimeSlot() {
     return this.timeSlot;
  }

  public void setTimeSlot(Set<TimeSlot> timeSlots) {
     this.timeSlot = timeSlots;
  }
  private ARMS arms;

  @ManyToOne(optional=false)
  public ARMS getArms() {
     return this.arms;
  }

  public void setArms(ARMS arms) {
     this.arms = arms;
  }

  
  private int technicianID;

  public void setTechnicianID(int value) {
  this.technicianID = value;
      }
  @Id
  public int getTechnicianID() {
  return this.technicianID;
         }

  //------------------------
  // CONSTRUCTOR
  //------------------------
/*
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
*/
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
  
  
  
  
  
  
  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "aRMS = "+(getArms()!=null?Integer.toHexString(System.identityHashCode(getArms())):"null");
  }
}