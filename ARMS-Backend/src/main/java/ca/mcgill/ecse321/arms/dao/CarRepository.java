package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Car;

public interface CarRepository extends CrudRepository<Car, String> {

}
