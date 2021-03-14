package ca.mcgill.ecse321.arms.dao;

import ca.mcgill.ecse321.arms.model.Customer;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Car;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, String> {
        Car findCarByPlateNo(String plateNo);

        Car deleteCarByPlateNo(String plateNo);

        List<Car> findCarsByCustomer(Customer customer);
}

