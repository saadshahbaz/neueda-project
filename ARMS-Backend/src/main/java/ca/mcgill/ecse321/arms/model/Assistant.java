/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

import javax.persistence.*;

// line 23 "../../../../../ARMS.ump"
@Entity
public class Assistant extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assistant Associations
	private ARMS arms;

	@OneToOne(optional=false)
	public ARMS getArms() {
	   return this.arms;
	}

	public void setArms(ARMS arms) {
	   this.arms = arms;
	}
  /*private int assistantID;
  
  public void setAssistantID(int value) {
	  this.assistantID = value;
   }
  @Id
  public int getAssistantID() {
	  return this.assistantID;
      }*/

  //------------------------
  // CONSTRUCTOR
  //------------------------
/*
  public Assistant(String aUsername, String aPassword, ARMS aARMS)
  {
    super(aUsername, aPassword);
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create assistant due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }
*/
  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
 

  /*public void delete()
  {
    ARMS existingARMS = arms;
    arms = null;
    if (existingARMS != null)
    {
      existingARMS.setAssistant(null);
    }
    super.delete();
  }*/

}