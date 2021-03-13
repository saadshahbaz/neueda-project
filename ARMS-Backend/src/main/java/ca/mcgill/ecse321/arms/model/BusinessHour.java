package ca.mcgill.ecse321.arms.model;

import javax.persistence.Entity;
import java.sql.Time;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BusinessHour {
    @Enumerated
    private DayOfWeek dayOfWeek;

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek value) {
        this.dayOfWeek = value;
    }

    private Time startTime;

    public void setStartTime(Time value) {
        this.startTime = value;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    private Time endTime;

    public void setEndTime(Time value) {
        this.endTime = value;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    private int businessHourID;

    public void setBusinessHourID(int value) {
        this.businessHourID = value;
    }

    @Id
    public int getBusinessHourID() {
        return this.businessHourID;
    }

    private ARMS ARMS;

    @ManyToOne(optional = false)
    public ARMS getARMS() {
        return this.ARMS;
    }

    private Business business;
    @ManyToOne(optional = false)
    public Business getBusiness() {
        return this.business;
    }

    public void setARMS(ARMS aRMS) {
        this.ARMS = aRMS;
    }
    public void setBusiness(Business business) {
        this.business = business;
    }

}
