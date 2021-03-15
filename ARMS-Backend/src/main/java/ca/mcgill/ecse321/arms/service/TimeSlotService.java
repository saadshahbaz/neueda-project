package ca.mcgill.ecse321.arms.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
//import static java.util.stream.Collectors.toList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.arms.model.*;
import ca.mcgill.ecse321.arms.dao.*;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public class TimeSlotService {
    @Autowired
    SpaceRepository spaceRepository;
    @Autowired
    TechnicianRepository technicianRepository;
    @Autowired
    TimeSlotRepository timeSlotRepository;
    @Autowired
    BussinessRepository businessRepository;


    @Transactional
    public Space createSpace(int ID){
        Space space = new Space();
        space.setSpaceID(ID);
        spaceRepository.save(space);
        return space;
    }

    @Transactional
    public Space getSpace(int ID){
        Space space = spaceRepository.findSpaceBySpaceID(ID);
        return space;
    }
    @Transactional
    public List<Space> getAllSpaces(){
        return toList(spaceRepository.findAll());
    }
    @Transactional
    public Technician createTechnician(int ID,String email, String name){
        Technician technician = new Technician();
        technician.setTechnicianID(ID);
        technician.setEmail(email);
        technician.setName(name);
        technicianRepository.save(technician);
        return technician;
    }
    @Transactional
    public Technician getTechnician(int ID){
        Technician technician = technicianRepository.findTechnicianByTechnicianID(ID);
        return technician;
    }
    @Transactional
    public List<Technician> getAllTechnicians(){
        return toList(technicianRepository.findAll());
    }

    @Transactional
    public TimeSlot createTimeSlot(String businessName,String startDate1, String startTime1, String endDate1, String endTime1,int spaceID,int technicianID){

        Date startDate = Date.valueOf(startDate1);
        Time startTime = Time.valueOf(startTime1);
        Date endDate = Date.valueOf(endDate1);
        Time endTime = Time.valueOf(endTime1);

        Business business = businessRepository.findBusinessByName(businessName);
        Space space = spaceRepository.findSpaceBySpaceID(spaceID);
        Technician technician = technicianRepository.findTechnicianByTechnicianID(technicianID);

        //Judge if has conflict with businesshour
        Set<BusinessHour> businessHours = business.getBusinessHour();
        List<BusinessHour> list_businessHour = new ArrayList<>(businessHours);

        //O(n)  comp
        int flag1 = check_hour(list_businessHour,startDate,startTime,endDate,endTime);

        //Judge if has conflict with space and tech
        Set<TimeSlot> timeSlots_space = space.getTimeSlot();
        Set<TimeSlot> timeSlots_tech = technician.getTimeSlot();
        List<TimeSlot> list_timeSlot_space = new ArrayList<>(timeSlots_space);
        List<TimeSlot> list_timeSlot_tech = new ArrayList<>(timeSlots_tech);

        int flag2 = check_slot(list_timeSlot_space,startDate,startTime,endDate,endTime);
        int flag3 = check_slot(list_timeSlot_tech,startDate,startTime,endDate,endTime);

        if(flag3+flag2+flag1 != 0){
            throw new IllegalArgumentException("cannot build such timeSlot!");
        }

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(startDate);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndDate(endDate);
        timeSlot.setEndTime(endTime);
        timeSlot.setTimeslotID(transfer(startDate, startTime));
        timeSlot.setSpace(space);
        timeSlot.setTechnician(technician);

        timeSlotRepository.save(timeSlot);
        return timeSlot;
    }
    @Transactional
    public List<TimeSlot> getAllTimeSlots(){
        return toList((timeSlotRepository.findAll()));
    }
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t:iterable){
            resultList.add(t);
        }
        return resultList;
    }

    //helper_check
    private int check_hour(List<BusinessHour> list_businessHour,Date startDate,Time startTime,Date endDate,Time endTime){
        //O(n)  comp
        int flag1 = 0; // if has conflict, flag1 = 1
        int i = 0;
        for (;i<list_businessHour.size();i++){
            if (!list_businessHour.get(i).getEndDate().before(startDate)){
                if(list_businessHour.get(i).getStartDate().after(startDate) || list_businessHour.get(i).getEndTime().before(endTime) || list_businessHour.get(i).getStartTime().after(startTime)){
                    flag1 = 1;
                    break;
                }
                int j = i;
                while(list_businessHour.get(j).getEndDate().before(endDate)){
                    j+=1;
                    if(j>list_businessHour.size()-1) {// if the final hour's endDate is before than input endDate
                        flag1 = 1;
                        break;
                    }
                    if(list_businessHour.get(j).getEndTime().before(endTime) || list_businessHour.get(j).getStartTime().after(startTime)){
                        flag1 = 1;
                        break;
                    }
                }
                break;
            }
        }
        if (i == list_businessHour.size()-1){ //visit all hour in hours but the endDate is before than the input startDate
            flag1 = 1;
        }
        return flag1;
    }
    private int check_slot(List<TimeSlot> list_slot,Date startDate,Time startTime,Date endDate,Time endTime){
        //O(n)  comp
        int flag1 = 0; // if has conflict, flag1 = 1
        int i = 0;
        for (;i<list_slot.size();i++){
            if (!list_slot.get(i).getEndDate().before(startDate)){
                if(list_slot.get(i).getStartDate().after(startDate) || list_slot.get(i).getEndTime().before(endTime) || list_slot.get(i).getStartTime().after(startTime)){
                    flag1 = 1;
                    break;
                }
                int j = i;
                while(list_slot.get(j).getEndDate().before(endDate)){
                    j+=1;
                    if(j>list_slot.size()-1) {// if the final hour's endDate is before than input endDate
                        flag1 = 1;
                        break;
                    }
                    if(list_slot.get(j).getEndTime().before(endTime) || list_slot.get(j).getStartTime().after(startTime)){
                        flag1 = 1;
                        break;
                    }
                }
                break;
            }
        }
        if (i == list_slot.size()-1){ //visit all hour in hours but the endDate is before than the input startDate
            flag1 = 1;
        }
        return flag1;
    }

    //helper_transferToInt
    private int transfer(Date startDate, Time startTime){
        String date = startDate.toString();
        String time = startTime.toString();
        String res = date+time;
        res = res.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "");
        return Integer.parseInt(res);
    }
}
