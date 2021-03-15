package ca.mcgill.ecse321.arms.dao;

import ca.mcgill.ecse321.arms.model.TimeSlot;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Customer;
import ca.mcgill.ecse321.arms.model.Technician;

import java.util.Set;

public interface TechnicianRepository extends CrudRepository<Technician, Integer>{
	Technician findTechnicianByTechnicianID(Integer technicianID);
	Set<TimeSlot> findTimeSlotsByTechnicianID(Integer technicianID);
}
