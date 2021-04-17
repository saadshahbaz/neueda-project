package ca.mcgill.ecse321.arms.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BusinessHour {
    private Date startDate;

    public void setStartDate(Date value) {
        this.startDate = value;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    private Date endDate;

    public void setEndDate(Date value) {
        this.endDate = value;
    }

    public Date getEndDate() {
        return this.endDate;
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

    private long businessHourID;

    public void setBusinessHourID(long value) {
        this.businessHourID = value;
    }

    @Id
    public long getBusinessHourID() {
        return this.businessHourID;
    }

    private Business business;

    @ManyToOne(optional = false)
    public Business getBusiness() {
        return this.business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

}
