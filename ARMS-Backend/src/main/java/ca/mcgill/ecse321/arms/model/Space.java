/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

import javax.persistence.*;

// line 37 "../../../../../ARMS.ump"
@Entity
public class Space
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Space Associations
	private Set<TimeSlot> timeSlot;

	@OneToMany(mappedBy="space",cascade = {CascadeType.ALL})
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
  
  private int spaceID;

  public void setSpaceID(int value) {
  this.spaceID = value;
      }
  @Id
  public int getSpaceID() {
  return this.spaceID;
         }

  //------------------------
  // CONSTRUCTOR
  //------------------------
/*
  public Space(ARMS aARMS)
  {
    timeslot = new ArrayList<TimeSlot>();
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create space due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }*/

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  
  
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeslot()
  {
    return 0;
  }
  
  /* Code from template association_SetOneToMany */
  

  

}