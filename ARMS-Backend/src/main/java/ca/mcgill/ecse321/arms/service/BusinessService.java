package ca.mcgill.ecse321.arms.service;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import static java.util.stream.Collectors.toList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.arms.model.*;
import ca.mcgill.ecse321.arms.dao.*;


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
        if (name == null || name == " " || name.isEmpty()) {
            throw new IllegalArgumentException("Business Name cannot be empty.");
        }
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
        if (name == null || name == " " || name.isEmpty()) {
            throw new IllegalArgumentException("Business Name cannot be empty");
        }
        Business business = businessRepository.findBusinessByName(name);
        if(business==null){
            throw new IllegalArgumentException("Business doesn't exist.");
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
     * @param startDate
     * @param endDate
     * @param startTime
     * @param endTime
     * @param business
     * @return
     */
    @Transactional
    public BusinessHour createBusinessHour(String startDate, String endDate, String startTime, String endTime, Business business) {
//creating businessHour with given parameters
        Date sDate = Date.valueOf(startDate);
        Date eDate = Date.valueOf(endDate);
        Time sTime = Time.valueOf(startTime);
        Time eTime = Time.valueOf(endTime);
        long id = transfer(sDate, sTime);
        System.out.println("the id is "+id);

        if(businessHourRepository.findBusinessHourByBusinessHourID(id)!=null){
            throw new IllegalArgumentException("BusinessHour id already exists");
        }
        if(sDate.after(eDate)){
            throw new IllegalArgumentException("Start date can not be after end date.");
        }
        if(business==null){
            throw new IllegalArgumentException("Business cannot be invalid.");
        }

        if(business.getBusinessHour()!=null){
            for(BusinessHour bh : business.getBusinessHour()){

                if((sDate.after(bh.getStartDate())&&sDate.before(bh.getEndDate()))||(eDate.after(bh.getStartDate())&&eDate.before(bh.getEndDate()))){
                    throw new IllegalArgumentException("BusinessHour time period cannot overlap with existing businessHours.");
                }


            }}

        BusinessHour businessHour = new BusinessHour();

        businessHour.setBusinessHourID(id);
        businessHour.setStartDate(sDate);
        businessHour.setEndDate(eDate);
        businessHour.setStartTime(sTime);
        businessHour.setEndTime(eTime);
        businessHour.setBusiness(business);
        business.addBusinessHour(businessHour);
        businessHourRepository.save(businessHour);
        businessRepository.save(business);


        System.out.println("the startDate of hour is :"+businessHour.getStartDate());
        return businessHour;
    }

    /**
     * helper method to transfer input startDate and startTime into a long id
     * @param startDate
     * @param startTime
     * @return
     */
    private long transfer(Date startDate, Time startTime){

        String date = startDate.toString();
        String time = startTime.toString();
        String res = date+time;
        res = res.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "");

        return Long.parseLong(res);
    }

    /**
     * Returns a businesshour that has the given id
     * @param  id
     * @return business
     */
    @Transactional
    public BusinessHour getBusinessHour(long id) {

        BusinessHour businessHour = businessHourRepository.findBusinessHourByBusinessHourID(id);
        if(businessHour==null){
            throw new IllegalArgumentException("BusinessHour doesn't exist");
        }
        return businessHour;
    }

    /**
     * delete a businessHour by id
     * @param id
     */
    @Transactional
    public Integer deleteBusinessHour(long id){
       return businessHourRepository.deleteBusinessHourByBusinessHourID(id);
    }

    /**
     * return a list of all businesses in the business repository
     * @return
     */
    @Transactional
    public List<BusinessHour> getAllBusinessHour() {
        return toList(businessHourRepository.findAll());
    }

    /**
     * get list of businessHours assiciated to the input business name
     * @param name
     * @return
     */
    @Transactional
    public List<BusinessHour> getBusinessHourByBusiness(String name){
        Business business = getBusiness(name);
        if(business==null){
            throw new IllegalArgumentException("Business doesn't exist.");
        } else{

            return businessHourRepository.findBusinessHourByBusiness(business);
        }
    }

    /**
     * helper method that turns iterables to list
     * @param iterable
     * @param <T>
     * @return
     */
    <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
