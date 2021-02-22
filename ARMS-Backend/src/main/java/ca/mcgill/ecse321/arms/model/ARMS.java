/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class ARMS{
private int armsID;
   
   public void setArmsID(int value) {
this.armsID = value;
    }
@Id
public int getArmsID() {
return this.armsID;
    }
private Set<TimeSlot> timeSlot;

@OneToMany(mappedBy="ARMS", cascade={CascadeType.ALL})
public Set<TimeSlot> getTimeSlot() {
   return this.timeSlot;
}

public void setTimeSlot(Set<TimeSlot> timeSlots) {
   this.timeSlot = timeSlots;
}

private Set<Space> space;

@OneToMany(mappedBy="ARMS", cascade={CascadeType.ALL})
public Set<Space> getSpace() {
   return this.space;
}

public void setSpace(Set<Space> spaces) {
   this.space = spaces;
}

private Set<Technician> technician;

@OneToMany(mappedBy="ARMS", cascade={CascadeType.ALL})
public Set<Technician> getTechnician() {
   return this.technician;
}

public void setTechnician(Set<Technician> technicians) {
   this.technician = technicians;
}

private Set<BusinessHour> businessHour;

@OneToMany(mappedBy="ARMS", cascade={CascadeType.ALL})
public Set<BusinessHour> getBusinessHour() {
   return this.businessHour;
}

public void setBusinessHour(Set<BusinessHour> businessHours) {
   this.businessHour = businessHours;
}

private Business business;

@OneToOne(mappedBy="ARMS", cascade={CascadeType.ALL})
public Business getBusiness() {
   return this.business;
}

public void setBusiness(Business business) {
   this.business = business;
}

private Set<Service> service;

@OneToMany(mappedBy="ARMS", cascade={CascadeType.ALL})
public Set<Service> getService() {
   return this.service;
}

public void setService(Set<Service> services) {
   this.service = services;
}

private Set<Car> car;

@OneToMany(mappedBy="ARMS", cascade={CascadeType.ALL})
public Set<Car> getCar() {
   return this.car;
}

public void setCar(Set<Car> cars) {
   this.car = cars;
}

private Set<Appointment> appointment;

@OneToMany(mappedBy="ARMS", cascade={CascadeType.ALL})
public Set<Appointment> getAppointment() {
   return this.appointment;
}

public void setAppointment(Set<Appointment> appointments) {
   this.appointment = appointments;
}

private Set<Bill> bill;

@OneToMany(mappedBy="ARMS", cascade={CascadeType.ALL})
public Set<Bill> getBill() {
   return this.bill;
}

public void setBill(Set<Bill> bills) {
   this.bill = bills;
}

private Set<Customer> customer;

@OneToMany(mappedBy="ARMS", cascade={CascadeType.ALL})
public Set<Customer> getCustomer() {
   return this.customer;
}

public void setCustomer(Set<Customer> customers) {
   this.customer = customers;
}

private Assistant assistant;

@OneToOne(mappedBy="ARMS", cascade={CascadeType.ALL})
public Assistant getAssistant() {
   return this.assistant;
}

public void setAssistant(Assistant assistant) {
   this.assistant = assistant;
}

}
