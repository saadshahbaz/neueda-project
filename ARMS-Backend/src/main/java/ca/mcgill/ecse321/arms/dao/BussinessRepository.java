package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Business;

public interface BussinessRepository extends CrudRepository<Business, String> {
    Business findBusinessByName(String name);
}
