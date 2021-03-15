package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.ArmsApplication;
import ca.mcgill.ecse321.arms.dao.AppointmentRepository;
import ca.mcgill.ecse321.arms.dao.CarRepository;
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
import java.util.List;

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

    /**
     * add an appointment to the ARMS
     *
     * @param service  service of the appointment
     * @param car      car of the appointment
     * @param timeSlot time slot of the appointment
     * @return the appointment that is added to the ARMS
     * @throws IllegalArgumentException
     * @author Grey Yuan
     */
    @Transactional
    public Appointment createAppointment(ca.mcgill.ecse321.arms.model.Service service, Car car, TimeSlot timeSlot) {
        String error = "";
        // parameter check
        if (service.toString().isEmpty()) {
            error += "No service is associated with the appointment\n";
        }
        if (car.toString().isEmpty()) {
            error += "No car is associated with the appointment\n";
        }
        if (timeSlot.toString().isEmpty()) {
            error += "No time slot is associated with the appointment";
        }
        if (error.length() > 0) throw new IllegalArgumentException(error);

        // if the appointment of the customer already exists
        Appointment appointment = appointmentRepository.findAppointmentByCar(car);
        if (service != null) {
            error = "appointment of " + car.getCustomer().toString() + " already exists";
            throw new IllegalArgumentException(error);
        }

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
    public void deleteAppointment(int appointmentID) {
        Appointment anAppointment = appointmentRepository.findAppointmentByAppointmentID(appointmentID);
        if(anAppointment==null){
            throw new IllegalArgumentException("No appointment was found.");
        }

        appointmentRepository.delete(anAppointment);
    }

    /**
     * update an appointment by indicating the appointment ID
     * @param appointmentID the appointment that will be updated
     * @param service new service of the appointment
     * @param car new car
     * @param timeSlot new time slot
     * @throws IllegalArgumentException
     * @return the appointment that is updated
     * @author Grey Yuan
     */
    @Transactional
    public Appointment updateService(int appointmentID, ca.mcgill.ecse321.arms.model.Service service, Car car, TimeSlot timeSlot){
        deleteAppointment(appointmentID);
        return createAppointment(service,car,timeSlot);
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
            throw new IllegalArgumentException("Appointment with ID" + appointmentID + " does not exist");
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
}