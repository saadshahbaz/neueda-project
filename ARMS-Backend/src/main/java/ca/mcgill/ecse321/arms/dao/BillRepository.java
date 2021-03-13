package ca.mcgill.ecse321.arms.dao;
import ca.mcgill.ecse321.arms.model.Customer;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.Bill;

import java.util.List;


public interface BillRepository extends CrudRepository<Bill, String> {
    Bill findBillByBillNo(int billNo);
    List<Bill> findBillsByCustomer(Customer customer);
}
