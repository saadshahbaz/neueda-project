package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.DayOfWeek;

import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;

public class BusinessDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private List<BusinessHourDto> businessHours;

    public BusinessDto() {
    }

    @SuppressWarnings("unchecked")
    public BusinessDto(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.businessHours = Collections.EMPTY_LIST;
    }

    public BusinessDto(String name, String address, String phoneNumber, String email, List<BusinessHourDto> businessHours) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.businessHours = businessHours;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public List<BusinessHourDto> getBusinessHour() {
        return businessHours;
    }

    public void setEvents(List<BusinessHourDto> businessHours) {
        this.businessHours = businessHours;
    }


}
