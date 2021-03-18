package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.Car;
import ca.mcgill.ecse321.arms.model.Service;
import ca.mcgill.ecse321.arms.model.TimeSlot;

public class AppointmentDto {
    private int ID;
    private Service service;
    private Car car;
    private TimeSlot timeslot;

    /**
     * @param service
     * @param car
     * @param timeslot
     * @author Grey Yuan
     */
    public AppointmentDto(int ID,Service service, Car car, TimeSlot timeslot){
        this.ID = ID;
        this.service = service;
        this.car = car;
        this.timeslot = timeslot;
    }

    public int getID() {
        return this.ID;
    }

    public Service getService() {
        return this.service;
    }

    public Car getCar() {
        return this.car;
    }

    public TimeSlot getTimeslot() {
        return this.timeslot;
    }
}
