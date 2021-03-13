package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Appointment;
import ca.mcgill.ecse321.arms.model.Customer;
import ca.mcgill.ecse321.arms.model.Car;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
	Appointment findAppointmentByAppointmentID(Integer appointmentID);
	Appointment findAppointmentByCar(Car car);
	List<Appointment> findAppointmentsByService(String serviceName);
}
