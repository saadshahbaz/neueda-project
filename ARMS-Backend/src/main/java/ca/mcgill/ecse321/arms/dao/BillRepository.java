package ca.mcgill.ecse321.arms.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Bill;


public interface BillRepository extends CrudRepository<Bill, String> {


}
