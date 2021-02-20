/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

// line 23 "../../../../../ARMS.ump"
public class Assistant extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assistant Associations
  private ARMS aRMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assistant(String aUsername, String aPassword, ARMS aARMS)
  {
    super(aUsername, aPassword);
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create assistant due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public ARMS getARMS()
  {
    return aRMS;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setARMS(ARMS aNewARMS)
  {
    boolean wasSet = false;
    if (aNewARMS == null)
    {
      //Unable to setARMS to null, as assistant must always be associated to a aRMS
      return wasSet;
    }
    
    Assistant existingAssistant = aNewARMS.getAssistant();
    if (existingAssistant != null && !equals(existingAssistant))
    {
      //Unable to setARMS, the current aRMS already has a assistant, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    ARMS anOldARMS = aRMS;
    aRMS = aNewARMS;
    aRMS.setAssistant(this);

    if (anOldARMS != null)
    {
      anOldARMS.setAssistant(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ARMS existingARMS = aRMS;
    aRMS = null;
    if (existingARMS != null)
    {
      existingARMS.setAssistant(null);
    }
    super.delete();
  }

}