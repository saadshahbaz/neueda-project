package ca.mcgill.ecse321.arms.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{
	Customer findCustomerByUsername(String username);
	Customer deleteCustomerByUsername(String username);

}
