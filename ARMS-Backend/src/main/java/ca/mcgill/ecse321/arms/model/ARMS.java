/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;

import javax.persistence.*;

import java.sql.Time;
import java.sql.Date;

// line 3 "../../../../../ARMS.ump"
@Entity
public class ARMS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

	
	private int id;
	   
	   public void setId(int value) {
	this.id = value;
	    }
	@Id
	public int getId() {
	return this.id;
	    }
  //ARMS Associations
	private Business business;

	@OneToOne(mappedBy="arms")
	public Business getBusiness() {
	   return this.business;
	}

	public void setBusiness(Business business) {
	   this.business = business;
	}

	/*private Assistant assistant;

	@OneToOne(mappedBy="arms")
	public Assistant getAssistant() {
	   return this.assistant;
	}

	public void setAssistant(Assistant assistant) {
	   this.assistant = assistant;
	}

	private Set<Customer> customer;

	@OneToMany(mappedBy="arms")
	public Set<Customer> getCustomer() {
	   return this.customer;
	}

	public void setCustomer(Set<Customer> customers) {
	   this.customer = customers;
	}
*/
	private Set<Technician> technician;

	@OneToMany(mappedBy="arms")
	public Set<Technician> getTechnician() {
	   return this.technician;
	}

	public void setTechnician(Set<Technician> technicians) {
	   this.technician = technicians;
	}

	private Set<Space> space;

	@OneToMany(mappedBy="arms")
	public Set<Space> getSpace() {
	   return this.space;
	}

	public void setSpace(Set<Space> spaces) {
	   this.space = spaces;
	}

	private Set<Car> car;

	@ManyToMany(mappedBy="arms")
	public Set<Car> getCar() {
	   return this.car;
	}

	public void setCar(Set<Car> cars) {
	   this.car = cars;
	}

	private Set<Service> service;

	@OneToMany(mappedBy="arms")
	public Set<Service> getService() {
	   return this.service;
	}

	public void setService(Set<Service> services) {
	   this.service = services;
	}

	private Set<Bill> bill;

	@OneToMany(mappedBy="arms")
	public Set<Bill> getBill() {
	   return this.bill;
	}

	public void setBill(Set<Bill> bills) {
	   this.bill = bills;
	}

	private Set<TimeSlot> timeSlot;

	@OneToMany(mappedBy="arms")
	public Set<TimeSlot> getTimeSlot() {
	   return this.timeSlot;
	}

	public void setTimeSlot(Set<TimeSlot> timeSlots) {
	   this.timeSlot = timeSlots;
	}
	
	private Set<User> user;

	@OneToMany(mappedBy="arms")
	public Set<User> getUser() {
	   return this.user;
	}

	public void setUser(Set<User> users) {
	   this.user = users;
	}
	/*
	private Set<BusinessHour> bussinessHour;

	@OneToMany(mappedBy="arms",cascade = {CascadeType.ALL})
	public Set<BusinessHour> getBussinessHour() {
	   return this.bussinessHour;
	}

	public void setBussinessHour(Set<BusinessHour> bussinessHours) {
	   this.bussinessHour = bussinessHours;
	}*/

	}