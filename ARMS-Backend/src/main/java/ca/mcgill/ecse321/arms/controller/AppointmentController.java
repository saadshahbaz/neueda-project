package ca.mcgill.ecse321.arms.controller;

import ca.mcgill.ecse321.arms.dao.AppointmentRepository;
import ca.mcgill.ecse321.arms.dto.AppointmentDto;
import ca.mcgill.ecse321.arms.dto.ServiceDto;
import ca.mcgill.ecse321.arms.model.Appointment;
import ca.mcgill.ecse321.arms.model.Car;
import ca.mcgill.ecse321.arms.model.Service;
import ca.mcgill.ecse321.arms.model.TimeSlot;
import ca.mcgill.ecse321.arms.service.AppointmentService;
import org.hibernate.loader.custom.ScalarResultColumnProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping(value={"/appointment","/appointment/"})
    public AppointmentDto createAppointment(
            @RequestParam("service") Service service,
            @RequestParam("car") Car car,
            @RequestParam("time slot") TimeSlot timeSlot
    ){
        ca.mcgill.ecse321.arms.model.Appointment appointment = appointmentService.createAppointment(service, car, timeSlot);
        return convertToDto(appointment);
    }

    private AppointmentDto convertToDto(Appointment appointment){
        if(appointment == null){
            throw new IllegalArgumentException("There is no such appointment");
        }
        AppointmentDto appointmentDto = new AppointmentDto(appointment.getService(),appointment.getCar(),appointment.getTimeSlot());
        return appointmentDto;
    }

}
