package ca.mcgill.ecse321.arms.dao;

import ca.mcgill.ecse321.arms.model.Space;
import ca.mcgill.ecse321.arms.model.Technician;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.TimeSlot;

import java.util.List;
import java.util.Set;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, String>{
    List<TimeSlot> findTimeSlotsBySpace(Space space);
    List<TimeSlot> findTimeSlotsByTechnician(Technician technician);
    TimeSlot findTimeSlotByTimeslotID(Long timeSlotID);
    Integer deleteTimeSlotByTimeslotID(Long timeSlotID);
}
