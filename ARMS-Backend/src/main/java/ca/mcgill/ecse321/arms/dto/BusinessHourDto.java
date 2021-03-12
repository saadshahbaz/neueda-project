package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.Business;
import ca.mcgill.ecse321.arms.model.DayOfWeek;

import java.sql.Date;
import java.sql.Time;



public class BusinessHourDto {
    private int id;
    private DayOfWeek dayOfWeek;
    private Time startTime;
    private Time endTime;
    private BusinessDto business;

    public BusinessHourDto() {
    }

    public BusinessHourDto(int id,DayOfWeek dayOfWeek, Time sTime, Time eTime, BusinessDto business) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = sTime;
        this.endTime = eTime;
        this.business = business;
    }


    public int getID() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public BusinessDto getBusiness(){
        return business;
    }


}
