package ca.mcgill.ecse321.arms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Service {
    private String name;

    public void setName(String value) {
        this.name = value;
    }

    @Id
    public String getName() {
        return this.name;
    }

    private int duration;

    public void setDuration(int value) {
        this.duration = value;
    }

    public int getDuration() {
        return this.duration;
    }

    private int price;

    public void setPrice(int value) {
        this.price = value;
    }

    public int getPrice() {
        return this.price;
    }

    private Set<Appointment> appointment;

    @OneToMany(mappedBy = "service")
    public Set<Appointment> getAppointment() {
        return this.appointment;
    }

    public void setAppointment(Set<Appointment> appointments) {
        this.appointment = appointments;
    }

//    private ARMS ARMS;
//
//    @ManyToOne(optional = false)
//    public ARMS getARMS() {
//        return this.ARMS;
//    }
//
//    public void setARMS(ARMS aRMS) {
//        this.ARMS = aRMS;
//    }

}
