package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.BusinessHour;

public interface BusinessHourRepository extends CrudRepository<BusinessHour, String> {

    BusinessHour findBusinessHourByBusinessHourID(long id);
    Integer deleteBusinessHourByBusinessHourID(long id);

}
