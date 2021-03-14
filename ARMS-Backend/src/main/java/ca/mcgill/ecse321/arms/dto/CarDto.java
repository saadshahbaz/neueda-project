package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.Customer;

public class CarDto {
    private String customer;
    private String plateNo;
    private String model;
    private String year;
    private String manufacturer;
    public CarDto(String customer, String plateNo, String manufacturer, String model, String year){
        this.customer=customer;
        this.manufacturer=manufacturer;
        this.model=model;
        this.plateNo=plateNo;
        this.year=year;
    }

    public String getCustomer() {
        return customer;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }


    public String getPlateNo() {
        return plateNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
