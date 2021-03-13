package ca.mcgill.ecse321.arms.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Customer extends User {
    private String lastReminder;

    public void setLastReminder(String value) {
        this.lastReminder = value;
    }

    public String getLastReminder() {
        return this.lastReminder;
    }

    private Set<Bill> bill;

    @OneToMany(mappedBy = "customer")
    public Set<Bill> getBill() {
        return this.bill;
    }

    public void setBill(Set<Bill> bills) {
        this.bill = bills;
    }

    private Set<Car> car;

    @OneToMany(mappedBy = "customer")
    public Set<Car> getCar() {
        return this.car;
    }

    public void setCar(Set<Car> cars) {
        this.car = cars;
    }

    private ARMS ARMS;

    @ManyToOne(optional = false)
    public ARMS getARMS() {
        return this.ARMS;
    }

    public void setARMS(ARMS aRMS) {
        this.ARMS = aRMS;
    }

    private int phoneNumber;

    public void setPhoneNumber(int value) {
        this.phoneNumber = value;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    private String email;

    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return this.email;
    }

}
