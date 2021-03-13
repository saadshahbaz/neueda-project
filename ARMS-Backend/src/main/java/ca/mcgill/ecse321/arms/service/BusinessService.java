package ca.mcgill.ecse321.arms.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
//import static java.util.stream.Collectors.toList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.arms.model.*;
import ca.mcgill.ecse321.arms.dao.*;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public class BusinessService {


    @Autowired
    private BusinessHourRepository businessHourRepository;

    @Autowired
    private BussinessRepository businessRepository;

    /**
     * create a business
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @return
     */
    @Transactional
    public Business createBusiness(String name, String address, String phoneNumber, String email) {
        //might need to check if ID(name) already exists & parameter validation
        //creating business with given parameters
        Business business = new Business();
        business.setAddress(address);
        business.setName(name);
        business.setEmail(email);
        business.setPhoneNumber(phoneNumber);
        businessRepository.save(business);
        return business;
    }

    /**
     * Returns a business that has the given business name
     * @param  name
     * @return business
     */
    @Transactional
    public Business getBusiness(String name) {
        if (name == null || name == "" || name.isBlank()) {
            throw new IllegalArgumentException("Business Name cannot be empty");
        }
        Business business = businessRepository.findBusinessByName(name);
        if(business==null){
            throw new IllegalArgumentException("Business doesn't exist");
        }
        return business;
    }

    /**
     * return a list of all businesses in the business repository
     * @return
     */
    @Transactional
    public List<Business> getAllBusiness() {
        return toList(businessRepository.findAll());
    }

    /**
     * create a businesshour
     * @param id
     * @param dayOfWeek
     * @param sTime
     * @param eTime
     * @param business
     * @return
     */
    @Transactional
    public BusinessHour createBusinessHour(int id,DayOfWeek dayOfWeek, Time sTime, Time eTime, Business business) {
        //might need to check if id already exists & parameter validation
        //creating businessHour with given parameters
        BusinessHour businessHour = new BusinessHour();
        businessHour.setBusinessHourID(id);
        businessHour.setDayOfWeek(dayOfWeek);
        businessHour.setStartTime(sTime);
        businessHour.setEndTime(eTime);
        businessHour.setBusiness(business);
        business.addBusinessHour(businessHour);
        businessHourRepository.save(businessHour);
        return businessHour;
    }

    /**
     * Returns a businesshour that has the given id
     * @param  id
     * @return business
     */
    @Transactional
    public BusinessHour getBusinessHour(int id) {

        BusinessHour businessHour = businessHourRepository.findBusinessHourByBusinessHourID(id);
        if(businessHour==null){
            throw new IllegalArgumentException("BusinessHour doesn't exist");
        }
        return businessHour;
    }

    /**
     * return a list of all businesses in the business repository
     * @return
     */
    @Transactional
    public List<BusinessHour> getAllBusinessHour() {
        return toList(businessHourRepository.findAll());
    }

    public List<BusinessHour> getBusinessHourByBusiness(String name){
        Business business = businessRepository.findBusinessByName(name);
        if(business==null){
            return null;
        } else{
            return toList(business.getBusinessHour());
        }
    }

    <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
