package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.DayOfWeek;

import java.sql.Date;
import java.sql.Time;

public class BusinessDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public BusinessDto() {
    }

    public BusinessDto(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
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


}
