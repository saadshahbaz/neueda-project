package ca.mcgill.ecse321.arms.controller;

import ca.mcgill.ecse321.arms.dao.TechnicianRepository;
import ca.mcgill.ecse321.arms.dto.TechnicianDto;
import ca.mcgill.ecse321.arms.model.Technician;
import ca.mcgill.ecse321.arms.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TechnicianController {
    @Autowired
    private TechnicianService technicianService;
    @Autowired
    private TechnicianRepository technicianRepository;

    @PostMapping(value = {"/technician", "/technician/"})
    public TechnicianDto createTechnician(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ) throws IllegalArgumentException{
        Technician technician = technicianService.createTechnician(name, email, id);
        return convertToDto(technician);
    }

    @PutMapping(value = {"/updateTechnician", "/updateTechnician/"})
    public TechnicianDto updateTechnician(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ) throws IllegalArgumentException{
        Technician technician = technicianService.updateTechnician(id, name, email);
        return convertToDto(technician);
    }

    @GetMapping(value = {"/technicians", "/technicians/"})
    public List<TechnicianDto> getAllTechnicians() throws IllegalArgumentException{
        List<TechnicianDto> technicianDtos = new ArrayList<>();
        for(Technician technician: technicianService.getAllTechnicians()){
            technicianDtos.add(convertToDto(technician));
        }
        return getAllTechnicians();
    }

    @DeleteMapping(value = {"/deleteTechnician", "/deleteTechnician/"})
    public void deleteTechnician(
            @RequestParam("id") int id
    ) throws IllegalArgumentException{
        technicianService.deleteTechnician(id);
    }

    public static TechnicianDto convertToDto(Technician technician){
        if(technician==null){
            throw new IllegalArgumentException("There is no such technician");
        }
        TechnicianDto technicianDto = new TechnicianDto(technician.getName(), technician.getEmail(), technician.getTechnicianID());
        return technicianDto;
    }

}
