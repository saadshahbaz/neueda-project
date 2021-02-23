package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Customer;
import ca.mcgill.ecse321.arms.model.Technician;

public interface TechnicianRepository extends CrudRepository<Technician, Integer>{
	Technician findTechnicianByTechnicianID(Integer technicianID);
}
