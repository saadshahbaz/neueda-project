package ca.mcgill.ecse321.arms.dto;

import java.sql.Date;
import java.sql.Time;

public class TimeSlotDto {
    private int id;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;

    private SpaceDto space;
    private TechnicianDto technician;

    public TimeSlotDto(){

    }

    public TimeSlotDto(int id, Date startDate, Time startTime, Date endDate, Time endTime,SpaceDto space, TechnicianDto technician){
        this.id = id;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.space = space;
        this.technician = technician;
    }

    public int getID() {return id;}
    public Date getStartDate(){ return startDate;}
    public Time getStartTime(){ return startTime;}
    public Date getEndDate(){return endDate;}
    public Time getEndTime(){return endTime;}
    public SpaceDto getSpace(){return space;}
    public TechnicianDto getTechnician(){return technician;}

    public void setID(int id){ this.id = id;}
    public void setStartDate(Date startDate){this.startDate = startDate;}
    public void setStartTime(Time startTime){this.startTime = startTime;}
    public void setEndDate(Date endDate){this.endDate = endDate;}
    public void setEndTime(Time endTime){this.endTime = endTime;}
    public void setSpace(SpaceDto space){this.space = space;}
    public void setTechnician(TechnicianDto technician){this.technician = technician;}
}
