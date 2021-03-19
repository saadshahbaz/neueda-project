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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping(value={"/appointment","/appointment/"})
    public AppointmentDto createAppointment(
            @RequestParam("appointmentID") int appointmentID,
            @RequestParam("serviceName") String serviceName,
            @RequestParam("plateNo") String plateNO,
            @RequestParam("businessName") String businessName,
            @RequestParam("startDate") String startDate,
            @RequestParam("startTime") String startTime,
            @RequestParam("endDate") String endDate,
            @RequestParam("endTime") String endTime,
            @RequestParam("technicianID") int technicianID,
            @RequestParam("spaceID") int spaceID
    ){
        Appointment appointment = appointmentService.createAppointment(appointmentID, serviceName, plateNO, businessName,
                startDate,startTime,endDate,endTime,spaceID,technicianID);
        return convertToDto(appointment);
    }

    @PutMapping(value = {"/updateAppointment","/updateAppointment/"})
    public AppointmentDto updateAppointment(
            @RequestParam("appointmentID") int appointmentID,
            @RequestParam("serviceName") String serviceName,
            @RequestParam("plateNo") String plateNO,
            @RequestParam("businessName") String businessName,
            @RequestParam("startDate") String startDate,
            @RequestParam("startTime") String startTime,
            @RequestParam("endDate") String endDate,
            @RequestParam("endTime") String endTime,
            @RequestParam("technicianID") int technicianID,
            @RequestParam("spaceID") int spaceID
    ){
        Appointment appointment = appointmentService.updateAppointment(appointmentID,serviceName, plateNO, businessName,
                startDate,startTime,endDate,endTime,spaceID,technicianID);
        return convertToDto(appointment);
    }

    @GetMapping(value = {"/getAppointments","/getAppointments/"})
    public List<AppointmentDto> getAllAppointments(){
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for(Appointment appointment : appointmentService.getAllAppointments()){
            appointmentDtos.add(convertToDto(appointment));
        }
        return appointmentDtos;
    }

    @DeleteMapping(value = {"/deleteAppointment", "/deleteAppointment/"})
    public void deleteAppointment(
            @RequestParam("appointmentID") int appointmentID
    ){
        appointmentService.deleteAppointment(appointmentID);
    }

    private AppointmentDto convertToDto(Appointment appointment){
        if(appointment == null){
            throw new IllegalArgumentException("There is no such appointment");
        }
        System.out.println("Here is row 82 in controller : id : "+appointment.getAppointmentID());
        AppointmentDto appointmentDto = new AppointmentDto(appointment.getAppointmentID(),appointment.getService().getName(),appointment.getCar().getPlateNo(),"ARM",appointment.getTimeSlot().getStartDate(),appointment.getTimeSlot().getStartTime(),appointment.getTimeSlot().getEndDate(),appointment.getTimeSlot().getEndTime(),appointment.getTimeSlot().getSpace().getSpaceID(),appointment.getTimeSlot().getTechnician().getTechnicianID());
        return appointmentDto;
    }

}
