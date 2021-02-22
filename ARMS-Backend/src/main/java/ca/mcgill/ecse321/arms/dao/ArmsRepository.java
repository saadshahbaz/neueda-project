package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.arms.model.ARMS;

public interface ArmsRepository extends CrudRepository<ARMS, Integer>{

}
