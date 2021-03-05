package ca.mcgill.ecse321.arms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Technician {
    private String name;

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    private int technicianID;

    public void setTechnicianID(int value) {
        this.technicianID = value;
    }

    @Id
    public int getTechnicianID() {
        return this.technicianID;
    }

    private Set<TimeSlot> timeSlot;

    @OneToMany(mappedBy = "technician")
    public Set<TimeSlot> getTimeSlot() {
        return this.timeSlot;
    }

    public void setTimeSlot(Set<TimeSlot> timeSlots) {
        this.timeSlot = timeSlots;
    }

    private ARMS ARMS;

    @ManyToOne(optional = false)
    public ARMS getARMS() {
        return this.ARMS;
    }

    public void setARMS(ARMS aRMS) {
        this.ARMS = aRMS;
    }

}
