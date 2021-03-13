package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Space;

public interface SpaceRepository extends CrudRepository<Space, Integer>{
	Space findSpaceBySpaceID(Integer spaceID);
}
