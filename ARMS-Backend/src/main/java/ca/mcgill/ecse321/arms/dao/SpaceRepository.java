package ca.mcgill.ecse321.arms.dao;

import ca.mcgill.ecse321.arms.model.TimeSlot;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Space;

import java.util.Set;

public interface SpaceRepository extends CrudRepository<Space, Integer>{
	Space findSpaceBySpaceID(Integer spaceID);
	Set<TimeSlot> findTimeSlotsBySpaceID(Integer spaceID);
}
