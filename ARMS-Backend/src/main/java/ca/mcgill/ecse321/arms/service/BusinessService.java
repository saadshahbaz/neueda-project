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
        //might need to check if id already exists & parameter validation
        //DateFormat Dateformatter = new SimpleDateFormat("yyyy-MM-dd");
        //DateFormat Timeformatter = new SimpleDateFormat("yyyy-MM-dd");


        //creating businessHour with given parameters
        Date sDate = Date.valueOf(startDate);
        Date eDate = Date.valueOf(endDate);
        Time sTime = Time.valueOf(startTime);
        Time eTime = Time.valueOf(endTime);
        int id = transfer(sDate, sTime);
        if(businessHourRepository.findBusinessHourByBusinessHourID(id)!=null){
            throw new IllegalArgumentException("BusinessHour id already exists");
        }
        if(sDate.after(eDate)){
            throw new IllegalArgumentException("Start date ban not be after end date.");
        }
        for(BusinessHour bh : getBusinessHourByBusiness(business.getName())){
            if((sDate.after(bh.getStartDate())&&sDate.before(bh.getEndDate()))||(eDate.after(bh.getStartDate())&&eDate.before(bh.getEndDate()))){
                throw new IllegalArgumentException("BusinessHour time period cannot overlap with existing businessHours.");
            }
        }

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
        return businessHour;
    }

    private int transfer(Date startDate, Time startTime){
        String date = startDate.toString();
        String time = startTime.toString();
        String res = date+time;
        res = res.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "");
        return Integer.parseInt(res);
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
     * delete a businessHour by id
     * @param id
     */
    public void deleteBusinessHour(int id){
        businessHourRepository.deleteBusinessHourByBusinessHourID(id);
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
