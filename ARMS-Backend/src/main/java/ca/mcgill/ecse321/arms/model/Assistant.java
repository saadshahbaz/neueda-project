/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Assistant extends User{
private ARMS ARMS;

@OneToOne(optional=false)
public ARMS getARMS() {
   return this.ARMS;
}

public void setARMS(ARMS aRMS) {
   this.ARMS = aRMS;
}

}
