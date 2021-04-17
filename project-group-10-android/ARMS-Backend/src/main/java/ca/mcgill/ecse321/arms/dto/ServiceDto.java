package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.Service;

public class ServiceDto {
    private String name;
    private int duration;
    private int price;

    /**
     * @param name
     * @param duration
     * @param price
     */
    public ServiceDto(String name, int duration, int price){
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public int getDuration(){
        return this.duration;
    }

    public int getPrice(){
        return this.price;
    }
}
