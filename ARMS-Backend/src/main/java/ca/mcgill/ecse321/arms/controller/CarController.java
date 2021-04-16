package ca.mcgill.ecse321.arms.controller;
import ca.mcgill.ecse321.arms.dao.CarRepository;
import ca.mcgill.ecse321.arms.dto.CarDto;
import ca.mcgill.ecse321.arms.model.Car;
import ca.mcgill.ecse321.arms.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CarController {
    @Autowired
    CarService carService;
    @Autowired
    CarRepository carRepository;
    @PostMapping(value = {"/car", "/car/"})
    public CarDto createCar(
            @RequestParam("customer")  String customer,
            @RequestParam("manufactuer") String manufacturer,
            @RequestParam("model") String  model,
            @RequestParam("year") String year,
            @RequestParam("plateN") String plateN
    ){
        Car car=carService.createCar(customer,manufacturer,model,year,plateN);
        return convertToDto(car);
    }

    @PutMapping(value = {"/updateCar", "/updateCar/"})
    public CarDto updateCar(
            @RequestParam("customer")  String customer,
            @RequestParam("manufactuer") String manufacturer,
            @RequestParam("model") String  model,
            @RequestParam("year") String year,
            @RequestParam("plateN") String plateN
    ){
        Car car = carService.updateCar(customer,manufacturer,model,year,plateN);
        return convertToDto(car);
    }

    @GetMapping(value = {"/getCar", "/getCar/"})
    public CarDto getCar(
            @RequestParam("plateNo") String plateNo
    ){
        Car car=carService.getCar(plateNo);
        return convertToDto(car);
    }

    @GetMapping(value = {"/getCarsByCustomer", "/getCarsByCustomer/"})
    public List<CarDto> getCarsByCustomer(
            @RequestParam("username") String username
    ){
        List<Car> cars=carService.getCarsByCustomer(username);
        List<CarDto> carDtos= new ArrayList<>();
        for(Car car:cars){
            CarDto carDto=convertToDto(car);
            carDtos.add(carDto);
        }
        return carDtos;
    }

    @DeleteMapping(value = {"/deleteCar", "/deleteCar/"})
    public Integer deleteCar(
            @RequestParam("plateNo") String plateNo
    ){
        return carService.deleteCar(plateNo);
    }

    public CarDto convertToDto(Car car){
        if(car==null){
            throw new IllegalArgumentException("There is no such car");
        }
        CarDto carDto = new CarDto(car.getCustomer().getUsername(),car.getPlateNo(),car.getManufacturer(),car.getModel(),car.getYear());
        return carDto;
    }

}

