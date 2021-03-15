package ca.mcgill.ecse321.arms.dao;

import ca.mcgill.ecse321.arms.model.Space;
import ca.mcgill.ecse321.arms.model.Technician;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.TimeSlot;

import java.util.Set;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, String>{
    Set<TimeSlot> findTimeSlotsBySpaceAndTechnician(Space space,Technician technician);
    Set<TimeSlot> findTimeSlotsBySpace(Space space);
    Set<TimeSlot> findTimeSlotsByTechnician(Technician technician);
    TimeSlot findTimeSlotByTimeslotID(Long timeSlotID);
}
