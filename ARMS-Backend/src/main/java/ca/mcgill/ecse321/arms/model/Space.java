package ca.mcgill.ecse321.arms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Space {
    private int spaceID;

    public void setSpaceID(int value) {
        this.spaceID = value;
    }

    @Id
    public int getSpaceID() {
        return this.spaceID;
    }

    private Set<TimeSlot> timeSlot;

    @OneToMany(mappedBy = "space")
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
