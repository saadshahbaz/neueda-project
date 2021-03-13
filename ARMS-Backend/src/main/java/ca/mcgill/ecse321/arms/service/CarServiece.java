package ca.mcgill.ecse321.arms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.arms.model.*;
import ca.mcgill.ecse321.arms.dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CarServiece {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Transactional
    public Car createCar(Customer customer, String manufacturer, String model, String year, String plateN)throws IllegalArgumentException{
        String error="";
        if (carRepository.findCarByPlateNo(plateN) != null) {
            error = "The car is already existed";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        Car car= new Car();
        car.setCustomer(customer);
        car.setManufacturer(manufacturer);
        car.setModel(model);
        car.setPlateNo(plateN);
        car.setYear(year);
        carRepository.save(car);
        return car;
    }
    @Transactional
    public void deleteCar(String plateNo)  throws IllegalArgumentException{
        String error = "";
        Car car = carRepository.findCarByPlateNo(plateNo);
        if (car==null){
            error="The plateNo does not exist";
        }else if(appointmentRepository.findAppointmentByCar(car)!=null){
            error="The car has appointments, not allowed to delete";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        carRepository.deleteCarByPlateNo(plateNo);
    }
    @Transactional
    public Car getCar(String plateNo) throws IllegalArgumentException{
        String error = "";
        if (plateNo == null) {
            error = "The plateNp cannot be empty";
        } else if(carRepository.findCarByPlateNo(plateNo)==null){
            error = "The car is not founded, check the plate number.";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        return carRepository.findCarByPlateNo(plateNo);
    }

    @Transactional
    public List<Car> getCarsByCustomer(Customer customer){
        return  carRepository.findCarsByCustomer(customer);
    }
    <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
