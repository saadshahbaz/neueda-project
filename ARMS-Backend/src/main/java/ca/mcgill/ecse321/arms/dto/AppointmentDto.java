package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.Car;
import ca.mcgill.ecse321.arms.model.Service;
import ca.mcgill.ecse321.arms.model.TimeSlot;

public class AppointmentDto {
    private Service service;
    private Car car;
    private TimeSlot timeslot;

    /**
     * @param service
     * @param car
     * @param timeslot
     * @author Grey Yuan
     */
    public AppointmentDto(Service service, Car car, TimeSlot timeslot){
        this.service = service;
        this.car = car;
        this.timeslot = timeslot;
    }

    public Service getService() {
        return service;
    }

    public Car getCar() {
        return car;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }
}
