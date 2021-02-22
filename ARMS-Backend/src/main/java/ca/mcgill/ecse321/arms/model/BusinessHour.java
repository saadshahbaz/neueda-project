package ca.mcgill.ecse321.arms.model;
import javax.persistence.Entity;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BusinessHour{

private Time starttime;

public void setStarttime(Time value) {
this.starttime = value;
    }
public Time getStarttime() {
return this.starttime;
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

@ManyToOne(optional=false)
public ARMS getARMS() {
   return this.ARMS;
}

public void setARMS(ARMS aRMS) {
   this.ARMS = aRMS;
}

}
