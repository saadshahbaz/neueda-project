package ca.mcgill.ecse321.arms.controller;

import ca.mcgill.ecse321.arms.dao.BussinessRepository;
import ca.mcgill.ecse321.arms.dto.BusinessDto;
import ca.mcgill.ecse321.arms.dto.BusinessHourDto;
import ca.mcgill.ecse321.arms.model.Business;
import ca.mcgill.ecse321.arms.model.BusinessHour;
import ca.mcgill.ecse321.arms.model.DayOfWeek;
import ca.mcgill.ecse321.arms.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.arms.dao.TimeSlotRepository;
import ca.mcgill.ecse321.arms.dto.BusinessDto;
import ca.mcgill.ecse321.arms.dto.CustomerDto;
import ca.mcgill.ecse321.arms.dto.SpaceDto;
import ca.mcgill.ecse321.arms.dto.TimeSlotDto;
import ca.mcgill.ecse321.arms.model.Business;
import ca.mcgill.ecse321.arms.model.Space;
import ca.mcgill.ecse321.arms.model.Technician;
import ca.mcgill.ecse321.arms.model.TimeSlot;
import ca.mcgill.ecse321.arms.service.SpaceService;
import ca.mcgill.ecse321.arms.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class TimeSlotController {
    @Autowired
    private TimeSlotService service;
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private SpaceController spaceController;
    @Autowired
    private  TechnicianController technicianController;

    @PostMapping(value = {"/timeSlot/{businessName}/{startDate}/{startTime}/{endDate}/{endTime}/{spaceID}/{technicianID}","/timeSlot/{startDate}/{startTime}/{endDate}/{endTime}/{spaceID}/{technicianID}/"})
    public TimeSlotDto createTimeSlot(@PathVariable("businessName") String businessName,
                                      @PathVariable("startDate") String startDate,
                                      @PathVariable("startTime") String startTime,
                                      @PathVariable("endDate") String endDate,
                                      @PathVariable("endTime") String endTime,
                                      @PathVariable("spaceID") int spaceID,
                                      @PathVariable("technicianID") int technicianID) throws IllegalArgumentException
    {
        System.out.println("HI");
        TimeSlot timeSlot = service.createTimeSlot(businessName,startDate,startTime,endDate,endTime,spaceID,technicianID);
        return convertToDto(timeSlot);
    }

    @DeleteMapping(value = {"/deleteTimeSlot", "/deleteTimeSlot/"})
    public void deleteTimeSlot(
            @RequestParam("timeSlotID") Long timeSlotID
    ){
        service.deleteTimeSlot(timeSlotID);
    }

    @GetMapping(value = {"/timeSlots","/timeSlots/"})
    public List<TimeSlotDto> getAllTimeSlots(){
        return service.getAllTimeSlots().stream().map(s -> convertToDto(s)).collect(Collectors.toList());
    }

    private TimeSlotDto convertToDto(TimeSlot timeSlot){
        if (timeSlot == null){
            throw new IllegalArgumentException("no such timeSlot!");
        }
        TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getTimeslotID(),timeSlot.getStartDate(),timeSlot.getStartTime(),timeSlot.getEndDate(),timeSlot.getEndTime(),spaceController.convertToDto(timeSlot.getSpace()),technicianController.convertToDto(timeSlot.getTechnician()));
        return timeSlotDto;
    }
}
