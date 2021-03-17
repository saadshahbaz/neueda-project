package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.ArmsApplication;
import ca.mcgill.ecse321.arms.dao.AppointmentRepository;
import ca.mcgill.ecse321.arms.dao.ServiceRepository;
import ca.mcgill.ecse321.arms.dao.TimeSlotRepository;
import ca.mcgill.ecse321.arms.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.arms.model.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    /**
     * add a service to the ARMS
     * @param serviceName name of the service
     * @param duration
     * @param price
     * @throws IllegalArgumentException
     * @return the service that is added to the ARMS
     * @author Cecilia Jiang
     */
    @Transactional
    public ca.mcgill.ecse321.arms.model.Service createService(String serviceName, int duration, int price) {
        String error = "";
        // parameter check
        if(serviceName.equals("")){
            error += "You must enter a service name\n";
        }
        if(duration<=0){
            error += "Duration must be positive\n";
        }
        if(price<=0){
            error += "Price must be positive";
        }
        if(error.length()>0)    throw new IllegalArgumentException(error);

        // if the service already exists
        ca.mcgill.ecse321.arms.model.Service service = serviceRepository.findServiceByName(serviceName.trim());
        if(service!=null){
            error = "service " + serviceName + " already exists";
            throw new IllegalArgumentException(error);
        }

        serviceName = serviceName.trim();
        ca.mcgill.ecse321.arms.model.Service aService = new ca.mcgill.ecse321.arms.model.Service();
        aService.setName(serviceName);
        aService.setDuration(duration);
        aService.setPrice(price);
        serviceRepository.save(aService);
        return aService;
    }

    /**
     * update a service by indicating the name of the service
     * @param curName the service that will be updated
     * @param duration new duration
     * @param price new price
     * @throws IllegalArgumentException
     * @return the service that is updated
     * @author Cecilia Jiang
     */
    @Transactional
    public ca.mcgill.ecse321.arms.model.Service updateService(String curName, int duration, int price) {
        String error = "";
        if(curName.equals("")){
            error += "You must enter a service name\n";
        }
        if(duration<=0){
            error += "Duration must be positive\n";
        }
        if(price<=0){
            error += "Price must be positive";
        }
        if(error.length()>0)    throw new IllegalArgumentException(error);

        ca.mcgill.ecse321.arms.model.Service aService = serviceRepository.findServiceByName(curName.trim());
        if(aService==null){
            throw new IllegalArgumentException("No service was found");
        }

        List<Appointment> appointments = appointmentRepository.findAppointmentsByService(aService);
        Date curDate = ArmsApplication.getCurrentDate();
        Time curTime = ArmsApplication.getCurrentTime();
        Boolean hasFutureAppointment = false;
        if(appointments!=null){
            for(int i=0; i<appointments.size(); i++){
                if(appointments.get(i).getTimeSlot().getEndDate().equals(curDate)){
                    if(appointments.get(i).getTimeSlot().getEndTime().after(curTime)){
                        hasFutureAppointment = true;
                    }
                }else if(appointments.get(i).getTimeSlot().getEndDate().after(curDate)){
                    hasFutureAppointment = true;
                }
            }
        }
        if(hasFutureAppointment){
            error = "You can not update a service contains future appointments";
            throw new IllegalArgumentException(error);
        }

        aService.setDuration(duration);
        aService.setPrice(price);
        serviceRepository.save(aService);
        return aService;
    }

    /**
     * delete a service in the ARMS
     * @param serviceName name of the service that will be deleted
     * @throws IllegalArgumentException
     * @author Cecilia Jiang
     */
    @Transactional
    public void deleteService(String serviceName) {
        String error = "";
        if(serviceName.equals("")){
            error += "You must enter a service name";
        }
        if(error.length()>0)    throw new IllegalArgumentException(error);
        ca.mcgill.ecse321.arms.model.Service aService = serviceRepository.findServiceByName(serviceName.trim());
        if(aService==null){
            throw new IllegalArgumentException("No service was found");
        }

        List<Appointment> appointments = appointmentRepository.findAppointmentsByService(aService);
        Date curDate = ArmsApplication.getCurrentDate();
        Time curTime = ArmsApplication.getCurrentTime();
        Boolean hasFutureAppointment = false;
        if(appointments!=null){
            for(int i=0; i<appointments.size(); i++){
                if(appointments.get(i).getTimeSlot().getEndDate().equals(curDate)){
                    if(appointments.get(i).getTimeSlot().getEndTime().after(curTime)){
                        hasFutureAppointment = true;
                    }
                }else if(appointments.get(i).getTimeSlot().getEndDate().after(curDate)){
                    hasFutureAppointment = true;
                }
            }
        }
        if(hasFutureAppointment){
            error = "You can not delete a service contains future appointments";
            throw new IllegalArgumentException(error);
        }
        serviceRepository.delete(aService);
    }

    /**
     * get a service with its name
     * @param name
     * @return the service that was found
     * @author Cecilia Jiang
     */
    @Transactional
    public ca.mcgill.ecse321.arms.model.Service getService(String name){
        ca.mcgill.ecse321.arms.model.Service service = serviceRepository.findServiceByName(name);
        if(service==null){
            throw new IllegalArgumentException("Service " + name + " does not exist");
        }
        return service;
    }

    /**
     * @return all the services in the ARMS
     * @author Cecilia Jiang
     */
    @Transactional
    public List<ca.mcgill.ecse321.arms.model.Service> getAllServices(){
        return toList(serviceRepository.findAll());
    }

    @Transactional
    public List<ca.mcgill.ecse321.arms.model.Service> sortServicesByPriceLtoH(){
        List<ca.mcgill.ecse321.arms.model.Service> services = toList(serviceRepository.findAll());
        Collections.sort(services, new CustomComparator1());
        return services;
    }

    @Transactional
    public List<ca.mcgill.ecse321.arms.model.Service> sortServicesByPriceHtoL(){
        List<ca.mcgill.ecse321.arms.model.Service> services = toList(serviceRepository.findAll());
        Collections.sort(services, new CustomComparator2());
        return services;
    }

    public class CustomComparator1 implements Comparator<ca.mcgill.ecse321.arms.model.Service> {
        @Override
        public int compare(ca.mcgill.ecse321.arms.model.Service o1, ca.mcgill.ecse321.arms.model.Service o2) {
            return Integer.compare(o1.getPrice(), o2.getPrice());
        }
    }

    public class CustomComparator2 implements Comparator<ca.mcgill.ecse321.arms.model.Service> {
        @Override
        public int compare(ca.mcgill.ecse321.arms.model.Service o1, ca.mcgill.ecse321.arms.model.Service o2) {
            return Integer.compare(o2.getPrice(), o1.getPrice());
        }
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
