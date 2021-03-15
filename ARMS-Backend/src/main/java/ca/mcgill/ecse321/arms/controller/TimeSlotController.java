package ca.mcgill.ecse321.arms.controller;

import ca.mcgill.ecse321.arms.dto.BusinessDto;
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

    @PostMapping(value = {"/timeSlot/{businessName}/{startTime}/{endTime}/{space}/{technician},/timeSlot/{timeSlotID}/{startDate}/{startTime}/{endDate}/{endTime}/{spaceID}/{technicianID}/"})
    public TimeSlotDto createTimeSlot(@PathVariable("businessName") String businessName,
                                      @PathVariable("startDate") String startDate,
                                      @PathVariable("startTime") String startTime,
                                      @PathVariable("endDate") String endDate,
                                      @PathVariable("endTime") String endTime,
                                      @PathVariable("spaceID") int spaceID,
                                      @PathVariable("technicianID") int technicianID) throws IllegalArgumentException
    {
        TimeSlot timeSlot = service.createTimeSlot(businessName,startDate,startTime,endDate,endTime,spaceID,technicianID);
        return convertToDto(timeSlot);
    }

    @GetMapping(value = {"/timeSlots","/timeSlots/"})
    public List<TimeSlotDto> getAlltimeSlots(){
        return service.getAllTimeSlots().stream().map(s -> convertToDto(s)).collect(Collectors.toList());
    }


    private TimeSlotDto convertToDto(TimeSlot timeSlot){
        if (timeSlot == null){
            throw new IllegalArgumentException("no such timeSlot!");
        }
        TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getTimeslotID(),timeSlot.getStartDate(),timeSlot.getStartTime(),timeSlot.getEndDate(),timeSlot.getEndTime(),SpaceController.convertToDto(timeSlot.getSpace()),TechnicianController.convertToDto(timeSlot.getTechnician()));
        return timeSlotDto;
    }
}
