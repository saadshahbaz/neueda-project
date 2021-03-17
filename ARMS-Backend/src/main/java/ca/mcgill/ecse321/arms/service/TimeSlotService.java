package ca.mcgill.ecse321.arms.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
//import static java.util.stream.Collectors.toList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.arms.model.*;
import ca.mcgill.ecse321.arms.dao.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    BusinessHourRepository businessHourRepository;


//    @Transactional
//    public Space createSpace(int ID){
//        Space space = new Space();
//        space.setSpaceID(ID);
//        spaceRepository.save(space);
//        return space;
//    }
//
//    @Transactional
//    public Space getSpace(int ID){
//        Space space = spaceRepository.findSpaceBySpaceID(ID);
//        return space;
//    }
//    @Transactional
//    public List<Space> getAllSpaces(){
//        return toList(spaceRepository.findAll());
//    }
//    @Transactional
//    public Technician createTechnician(int ID,String email, String name){
//        Technician technician = new Technician();
//        technician.setTechnicianID(ID);
//        technician.setEmail(email);
//        technician.setName(name);
//        technicianRepository.save(technician);
//        return technician;
//    }
//    @Transactional
//    public Technician getTechnician(int ID){
//        Technician technician = technicianRepository.findTechnicianByTechnicianID(ID);
//        return technician;
//    }
//    @Transactional
//    public List<Technician> getAllTechnicians(){
//        return toList(technicianRepository.findAll());
//    }

    //appointment1 --> createTimeSLot (ARM,2020-03-23,10:00:00,2020-03-23,12:00:00,3,5)

    @Transactional
    public TimeSlot createTimeSlot(String businessName,String startDate1, String startTime1, String endDate1, String endTime1,int spaceID,int technicianID){

        Date startDate = Date.valueOf(startDate1);
        Time startTime = Time.valueOf(startTime1);
        Date endDate = Date.valueOf(endDate1);
        Time endTime = Time.valueOf(endTime1);

        Business business = businessRepository.findBusinessByName(businessName);
        Space space = spaceRepository.findSpaceBySpaceID(spaceID);
        Technician technician = technicianRepository.findTechnicianByTechnicianID(technicianID);

        System.out.println("Hi*2");
        //Judge if has conflict with businesshour
        List<BusinessHour> list_businessHour = businessHourRepository.findBusinessHourByBusiness(business);
        Stream<BusinessHour> sorted1 = list_businessHour.stream().sorted(Comparator.comparing(BusinessHour::getBusinessHourID));
        List<BusinessHour> list_businessHour_sorted = sorted1.collect(Collectors.toList());

        System.out.println(list_businessHour_sorted.get(0).getBusinessHourID()+"and "+list_businessHour_sorted.get(list_businessHour_sorted.size()-1).getBusinessHourID());
        //O(n)  comp
        int flag1 = check_hour(list_businessHour_sorted,startDate,startTime,endDate,endTime);

        if(flag1 != 0){
            System.out.println("Here");
            throw new IllegalArgumentException("cannot build such timeSlot since no free businessHour!");
        }

        //Judge if has conflict with space and tech
        List<TimeSlot> list_timeSlots_Space = timeSlotRepository.findTimeSlotsBySpace(space);
        List<TimeSlot> list_timeSlots_Tech = timeSlotRepository.findTimeSlotsByTechnician(technician);

        Stream<TimeSlot> sorted2 = list_timeSlots_Space.stream().sorted(Comparator.comparing(TimeSlot::getTimeslotID));
        List<TimeSlot> list_timeSlot_Space_sorted = sorted2.collect(Collectors.toList());

        Stream<TimeSlot> sorted3 = list_timeSlots_Tech.stream().sorted(Comparator.comparing(TimeSlot::getTimeslotID));
        List<TimeSlot> list_timeSlot_Tech_sorted = sorted3.collect(Collectors.toList());

        int flag2 = check_slot(list_timeSlot_Space_sorted,startDate,startTime,endDate,endTime);
        int flag3 = check_slot(list_timeSlot_Tech_sorted,startDate,startTime,endDate,endTime);

        if(flag2 != 0){
            throw new IllegalArgumentException("cannot build such timeSlot since no free space!");
        }
        if(flag3 != 0){
            throw new IllegalArgumentException("cannot build such timeSlot since no free tech !");
        }

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(startDate);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndDate(endDate);
        timeSlot.setEndTime(endTime);
        timeSlot.setTimeslotID(transfer(startDate, startTime)*100+spaceID*10+technicianID);
        timeSlot.setSpace(space);
        timeSlot.setTechnician(technician);
        System.out.println("itmeSlotID is "+timeSlot.getTimeslotID());

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


    @Transactional
    public Integer deleteTimeSlot(Long timeSlotID){
        return timeSlotRepository.deleteTimeSlotByTimeslotID(timeSlotID);
    }

    //helper_check
    private int check_hour(List<BusinessHour> list_businessHour,Date startDate,Time startTime,Date endDate,Time endTime){
        //O(n)  comp
        int flag1 = 0; // if has conflict, flag1 = 1
        int i = 0;
        int q = 0;
        for(;i<list_businessHour.size();i++){
            if (!list_businessHour.get(i).getStartDate().after(startDate) && !list_businessHour.get(i).getEndDate().before(endDate)){
                q=1;
                System.out.println("i is "+i+"id is "+list_businessHour.get(i).getBusinessHourID());
                if(list_businessHour.get(i).getEndTime().before(endTime) || list_businessHour.get(i).getStartTime().after(startTime)){
                    System.out.println(list_businessHour.get(i).getEndTime().before(endTime)+" and "+list_businessHour.get(i).getStartTime().after(startTime));
                    flag1 = 1;
                    return flag1;
                }
            }
        }
        if(q == 0){ flag1 = 1;}
        return flag1;
    }
    private int check_slot(List<TimeSlot> list_slot,Date startDate,Time startTime,Date endDate,Time endTime){
        //O(n)  comp
        int flag2 = 0; // if has conflict, flag1 = 1
        int i = 0;
        System.out.println(list_slot.size());
        for(;i<list_slot.size();i++){
            System.out.println(list_slot.get(i));
            if (list_slot.get(i).getStartDate().equals(startDate)){
                if(list_slot.get(i).getEndTime().before(startTime) || list_slot.get(i).getStartTime().after(endTime)){
                    flag2 = 0;
                }
                else {
                    flag2 = 1;
                    return flag2;
                }
            }
        }
        return flag2;
    }


    //helper_transferToLong
    private Long transfer(Date startDate, Time startTime){
        String date = startDate.toString();
        String time = startTime.toString();
        String res = date+time;
        res = res.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "");
        return Long.parseLong(res);
    }
}
