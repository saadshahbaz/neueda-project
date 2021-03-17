package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.ArmsApplication;
import ca.mcgill.ecse321.arms.dao.*;
import ca.mcgill.ecse321.arms.model.Appointment;
import ca.mcgill.ecse321.arms.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.arms.model.*;

import java.sql.Date;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AppointmentService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BusinessHourRepository businessHourRepository;
    @Autowired
    private BussinessRepository bussinessRepository;
    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
    private TechnicianRepository technicianRepository;

    /**
     * add an appointment to the ARMS
     *
     * @param serviceName  service name of the appointment
     * @param plateNo      car plate number of the appointment
     * @param businessName business name of the appointment
     * @param startDate
     * @param startTime
     * @param endDate
     * @param endTime
     * @param spaceID
     * @param technicianID
     * @return the appointment that is added to the ARMS
     * @throws IllegalArgumentException
     * @author Grey Yuan
     */
    @Transactional
    public Appointment createAppointment(String serviceName, String plateNo,
                                         String businessName,String startDate, String startTime,
                                         String endDate, String endTime,int spaceID,int technicianID) {
        String error = "";
        // parameter check
        if(serviceName.equals("")){
            error += "You must enter a service name\n";
        }
        if (plateNo.equals("")) {
            error += "You must enter a plate number\n";
        }
        if (businessName==null || startDate==null || startTime==null || endDate==null || endTime==null){
            error = "The time slot parameter cannot be empty";
        }
        if (serviceRepository.findServiceByName(serviceName) == null) {
            error = "The service does not exist";
        }
        if (carRepository.findCarByPlateNo(plateNo) == null) {
            error = "The car does not exist";
        }
        if (error.length() > 0) throw new IllegalArgumentException(error);

        //find the intermediate objects with given paremeter
        ca.mcgill.ecse321.arms.model.Service service = serviceRepository.findServiceByName(serviceName);
        Car car = carRepository.findCarByPlateNo(plateNo);
        TimeSlot timeSlot = createTimeSlot(businessName,startDate,startTime,endDate,endTime,spaceID,technicianID);



        Appointment anAppointment = new Appointment();
        anAppointment.setService(service);
        anAppointment.setCar(car);
        anAppointment.setTimeSlot(timeSlot);
        appointmentRepository.save(anAppointment);
        return anAppointment;

    }

    /**
     * delete an appointment in the ARMS
     * @param  appointmentID ID of the appointment that will be deleted
     * @throws IllegalArgumentException
     * @author Grey Yuan
     */
    @Transactional
    public int deleteAppointment(int appointmentID) {
        String error = "";
        Appointment anAppointment = appointmentRepository.findAppointmentByAppointmentID(appointmentID);
        if(anAppointment==null){
            throw new IllegalArgumentException("No appointment was found.");
        }
        //only be able to cancel 24hr before the appointment starts
        Date appointmentDate = anAppointment.getTimeSlot().getStartDate();
        Date curDate = ArmsApplication.getCurrentDate();
        if (appointmentDate.equals(curDate)){
            error = "You can not delete an appointment happening on the current date";
            throw new IllegalArgumentException(error);
        }

        appointmentRepository.delete(anAppointment);
        return appointmentID;
    }

    /**
     * update an appointment by indicating the appointment ID
     * @param appointmentID the appointment that will be updated
     * @param serviceName  service name of the appointment
     * @param plateNo      car plate number of the appointment
     * @param businessName business name of the appointment
     * @param startDate
     * @param startTime
     * @param endDate
     * @param endTime
     * @param spaceID
     * @param technicianID
     * @throws IllegalArgumentException
     * @return the appointment that is updated
     * @author Grey Yuan
     */
    @Transactional
    public Appointment updateAppointment(int appointmentID, String serviceName, String plateNo,
                                     String businessName,String startDate, String startTime,
                                     String endDate, String endTime,int spaceID,int technicianID){
        deleteAppointment(appointmentID);
        return createAppointment(serviceName,plateNo,businessName,startDate,startTime,endDate,endTime,spaceID,technicianID);
    }

    /**
     * get a appointment with its ID
     * @param appointmentID
     * @return the appointment that was found
     * @author Grey Yuan
     */
    @Transactional
    public Appointment getAppointment(int appointmentID){
        Appointment appointment = appointmentRepository.findAppointmentByAppointmentID(appointmentID);
        if(appointment==null){
            throw new IllegalArgumentException("Appointment with ID " + appointmentID + " does not exist");
        }
        return appointment;
    }

    /**
     * @return all the appointments in the ARMS
     * @author Grey Yuan
     */
    @Transactional
    public List<Appointment> getAllAppointments(){
        return toList(appointmentRepository.findAll());
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

    public TimeSlot createTimeSlot(String businessName,String startDate1, String startTime1, String endDate1, String endTime1,int spaceID,int technicianID){

        Date startDate = Date.valueOf(startDate1);
        Time startTime = Time.valueOf(startTime1);
        Date endDate = Date.valueOf(endDate1);
        Time endTime = Time.valueOf(endTime1);

        Business business = bussinessRepository.findBusinessByName(businessName);
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
            throw new IllegalArgumentException("cannot build such appointment since no free businessHour!");
        }

        //Judge if has conflict with space and tech
        List<TimeSlot> list_timeSlot_Space = timeSlotRepository.findTimeSlotsBySpace(space);
        List<TimeSlot> list_timeSlot_Tech = timeSlotRepository.findTimeSlotsByTechnician(technician);

        Stream<TimeSlot> sorted2 = list_timeSlot_Space.stream().sorted(Comparator.comparing(TimeSlot::getTimeslotID));
        List<TimeSlot> list_timeSlot_Space_sorted = sorted2.collect(Collectors.toList());

        Stream<TimeSlot> sorted3 = list_timeSlot_Tech.stream().sorted(Comparator.comparing(TimeSlot::getTimeslotID));
        List<TimeSlot> list_timeSlot_Tech_sorted = sorted3.collect(Collectors.toList());

        int flag2 = check_slot(list_timeSlot_Space_sorted,startDate,startTime,endDate,endTime);
        int flag3 = check_slot(list_timeSlot_Tech_sorted,startDate,startTime,endDate,endTime);

        if(flag2 != 0){
            throw new IllegalArgumentException("cannot build such appointment since no free space!");
        }
        if(flag3 != 0){
            throw new IllegalArgumentException("cannot build such appointment since no free tech !");
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
        for(;i<list_slot.size();i++){
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

    private Long transfer(Date startDate, Time startTime){
        String date = startDate.toString();
        String time = startTime.toString();
        String res = date+time;
        res = res.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "");
        return Long.parseLong(res);
    }

}