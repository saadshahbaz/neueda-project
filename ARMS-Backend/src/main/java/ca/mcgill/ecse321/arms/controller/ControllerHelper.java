package ca.mcgill.ecse321.arms.controller;

import ca.mcgill.ecse321.arms.dto.BusinessDto;
import ca.mcgill.ecse321.arms.dto.BusinessHourDto;
import ca.mcgill.ecse321.arms.model.Business;
import ca.mcgill.ecse321.arms.model.BusinessHour;
import ca.mcgill.ecse321.arms.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

//collection of convertToDto methods

@CrossOrigin(origins = "*")
@RestController
public class ControllerHelper {

    public BusinessDto convertToDto(Business business) {
        if (business == null) {
            throw new IllegalArgumentException("There is no such Business!");
        }
        BusinessDto businessDto = new BusinessDto(business.getName(),business.getAddress(),business.getPhoneNumber(),business.getEmail());
        return businessDto;
    }

    public BusinessHourDto convertToDto(BusinessHour businessHour) {
        if (businessHour == null) {
            throw new IllegalArgumentException("There is no such Business!");
        }
        BusinessHourDto businessHourDto = new BusinessHourDto(businessHour.getBusinessHourID(),businessHour.getStartDate(),businessHour.getEndDate(),businessHour.getStartTime(),businessHour.getEndTime(),convertToDto(businessHour.getBusiness()));
        System.out.println(businessHourDto.getID());
        return businessHourDto;
    }



}
