package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Service;

public interface ServiceRepository extends CrudRepository<Service, String>{

}
