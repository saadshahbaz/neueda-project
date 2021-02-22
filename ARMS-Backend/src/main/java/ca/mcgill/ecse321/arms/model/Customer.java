package ca.mcgill.ecse321.arms.model;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Customer extends User{
private Integer lastReminder;
   
   public void setLastReminder(Integer value) {
this.lastReminder = value;
    }
public Integer getLastReminder() {
return this.lastReminder;
    }
private Set<Bill> bill;

@OneToMany(mappedBy="customer")
public Set<Bill> getBill() {
   return this.bill;
}

public void setBill(Set<Bill> bills) {
   this.bill = bills;
}

private Set<Car> car;

@OneToMany(mappedBy="customer")
public Set<Car> getCar() {
   return this.car;
}

public void setCar(Set<Car> cars) {
   this.car = cars;
}

private ARMS ARMS;

@ManyToOne(optional=false)
public ARMS getARMS() {
   return this.ARMS;
}

public void setARMS(ARMS aRMS) {
   this.ARMS = aRMS;
}

}
