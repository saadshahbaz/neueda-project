/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

import javax.persistence.*;

// line 17 "../../../../../ARMS.ump"
@Entity
@Table(name="users")
public abstract class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  //private static Map<String, User> usersByUsername = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String username;
  
  public void setUsername(String value) {
this.username = value;
   }
@Id
public String getUsername() {
return this.username;
   }


private ARMS arms;

@ManyToOne(cascade = {CascadeType.ALL})
public ARMS getArms() {
   return this.arms;
}

public void setArms(ARMS arms) {
   this.arms = arms;
}
  private String password;

  //------------------------
  // CONSTRUCTOR
  //------------------------
/*
  public User(String aUsername, String aPassword)
  {
    password = aPassword;
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }
*/
  //------------------------
  // INTERFACE
  //------------------------

  
  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  
  public String getPassword()
  {
    return password;
  }

  /*public void delete()
  {
    usersByUsername.remove(getUsername());
  }

*/
  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}