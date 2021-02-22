package ca.mcgill.ecse321.arms.model;
import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TimeSlot{
private Date startDate;
   
   public void setStartDate(Date value) {
this.startDate = value;
    }
public Date getStartDate() {
return this.startDate;
    }
private Time startTime;

public void setStartTime(Time value) {
this.startTime = value;
    }
public Time getStartTime() {
return this.startTime;
    }
private Date endDate;

public void setEndDate(Date value) {
this.endDate = value;
    }
public Date getEndDate() {
return this.endDate;
    }
private Time endTime;

public void setEndTime(Time value) {
this.endTime = value;
    }
public Time getEndTime() {
return this.endTime;
    }
private int timeslotID;

public void setTimeslotID(int value) {
this.timeslotID = value;
    }
@Id
public int getTimeslotID() {
return this.timeslotID;
    }
private Space space;

@ManyToOne
public Space getSpace() {
   return this.space;
}

public void setSpace(Space space) {
   this.space = space;
}

private Technician technician;

@ManyToOne
public Technician getTechnician() {
   return this.technician;
}

public void setTechnician(Technician technician) {
   this.technician = technician;
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
