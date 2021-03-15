package ca.mcgill.ecse321.arms.dao;

import ca.mcgill.ecse321.arms.model.Bill;
import ca.mcgill.ecse321.arms.model.Business;
import ca.mcgill.ecse321.arms.model.Customer;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.BusinessHour;

import java.util.List;

public interface BusinessHourRepository extends CrudRepository<BusinessHour, String> {

    BusinessHour findBusinessHourByBusinessHourID(long id);
    Integer deleteBusinessHourByBusinessHourID(long id);
    List<BusinessHour> findBusinessHourByBusiness(Business business);


}
