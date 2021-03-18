package ca.mcgill.ecse321.arms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.arms.model.*;
import ca.mcgill.ecse321.arms.dao.*;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * create car with the input parameters
     * @param customer
     * @param manufacturer
     * @param model
     * @param year
     * @param plateN
     * @return car
     * @author Zhiwei Li
     * @throws IllegalArgumentException
     */
    @Transactional
    public Car createCar(String customer, String manufacturer, String model, String year, String plateN) throws IllegalArgumentException{
        String error="";
        if (carRepository.findCarByPlateNo(plateN) != null) {
            error = "The car is already existed";
        }else if (customerRepository.findCustomerByUsername(customer) == null) {
            error = "The customer does not exist";
        }else if (model==null || year==null || plateN==null){
            error = "The parameter cannot be empty";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        Car car= new Car();
        car.setCustomer(customerRepository.findCustomerByUsername(customer));
        car.setManufacturer(manufacturer);
        car.setModel(model);
        car.setPlateNo(plateN);
        car.setYear(year);
        carRepository.save(car);
        return car;
    }

    /**
     * update car with input plate number with the given parameters
     * @param customer
     * @param manufacturer
     * @param model
     * @param year
     * @param plateN
     * @return car
     * @author Zhiwei Li
     * @throws IllegalArgumentException
     */
    @Transactional
    public Car updateCar(String customer, String manufacturer, String model, String year, String plateN) throws IllegalArgumentException{
        String error="";
        if (carRepository.findCarByPlateNo(plateN) == null) {
            error = "The car does not exist";
        }else if (customerRepository.findCustomerByUsername(customer) == null) {
            error = "The customer does not exist";
        }else if (model==null || year==null || plateN==null){
            error = "The parameter cannot be empty";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        Car car= carRepository.findCarByPlateNo(plateN);
        car.setCustomer(customerRepository.findCustomerByUsername(customer));
        car.setManufacturer(manufacturer);
        car.setModel(model);
        car.setPlateNo(plateN);
        car.setYear(year);
        carRepository.save(car);
        return car;
    }

    /**
     * delete car with input plate number
     * @param plateNo
     * @return
     * @author Zhiwei Li
     * @throws IllegalArgumentException
     */
    @Transactional
    public Integer deleteCar(String plateNo)  throws IllegalArgumentException{
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
        return carRepository.deleteCarByPlateNo(plateNo);
    }

    /**
     * get car with input plate number
     * @param plateNo
     * @return car
     * @author Zhiwei Li
     * @throws IllegalArgumentException
     */
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

    /**
     * get list of cars associated with the input customer
     * @param customer
     * @return
     * @author Zhiwei Li
     */
    @Transactional
    public List<Car> getCarsByCustomer(String customer){
        return  carRepository.findCarsByCustomer(customerRepository.findCustomerByUsername(customer));
    }

}
