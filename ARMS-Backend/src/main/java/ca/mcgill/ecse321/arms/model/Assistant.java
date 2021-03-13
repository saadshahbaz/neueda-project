package ca.mcgill.ecse321.arms.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Assistant extends User {
   private ARMS ARMS;

   @ManyToOne(optional = false)
   public ARMS getARMS() {
      return this.ARMS;
   }

   public void setARMS(ARMS aRMS) {
      this.ARMS = aRMS;
   }

}
