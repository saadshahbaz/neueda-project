package ca.mcgill.ecse321.arms.controller;

import ca.mcgill.ecse321.arms.dao.ServiceRepository;
import ca.mcgill.ecse321.arms.dto.ServiceDto;
import ca.mcgill.ecse321.arms.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping(value = {"/service","/service/"})
    public ServiceDto createService(
            @RequestParam("name") String name,
            @RequestParam("duration") int duration,
            @RequestParam("price") int price
    ){
        ca.mcgill.ecse321.arms.model.Service service = serviceService.createService(name, duration, price);
        return convertToDto(service);
    }

    @PutMapping(value = {"/updateService","/updateService/"})
    public ServiceDto updateService(
            @RequestParam("name") String curName,
            @RequestParam("newName") String newName,
            @RequestParam("duration") int duration,
            @RequestParam("price") int price
    ){
        ca.mcgill.ecse321.arms.model.Service service = serviceService.updateService(curName, newName, duration, price);
        return convertToDto(service);
    }

    @GetMapping(value = {"/services","/services/"})
    public List<ServiceDto> getAllServices(){
        List<ServiceDto> serviceDtos = new ArrayList<>();
        for(ca.mcgill.ecse321.arms.model.Service service : serviceService.getAllServices()){
            serviceDtos.add(convertToDto(service));
        }
        return serviceDtos;
    }

    @DeleteMapping(value = {"/deleteService", "/deleteService/"})
    public void deleteService(
            @RequestParam("name") String name
    ){
        serviceService.deleteService(name);
    }

    private ServiceDto convertToDto(ca.mcgill.ecse321.arms.model.Service service){
        if(service == null){
            throw new IllegalArgumentException("There is no such service");
        }
        ServiceDto serviceDto = new ServiceDto(service.getName(), service.getDuration(), service.getPrice());
        return serviceDto;
    }
}