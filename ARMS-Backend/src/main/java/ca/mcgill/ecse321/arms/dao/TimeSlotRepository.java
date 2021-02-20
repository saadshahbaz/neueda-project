package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, String>{

}
