package ca.mcgill.ecse321.arms.controller;

import ca.mcgill.ecse321.arms.dao.AppointmentRepository;
import ca.mcgill.ecse321.arms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;

}
