package ca.mcgill.ecse321.arms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.arms.model.*;
import ca.mcgill.ecse321.arms.dao.*;

import java.util.ArrayList;
import java.util.List;
@Service
public class BillService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BillRepository billRepository;

    /**
     * create a bill with the given parameters
     * @param username
     * @param billNo
     * @param amount
     * @return bill
     * @author Zhiwei Li
     * @throws IllegalArgumentException
     */
    @Transactional
    public Bill createBill(String username, int billNo, int amount) throws IllegalArgumentException{
        String error="";
        if (billRepository.findBillByBillNo(billNo)!=null){
            error="the billNo is already existed";
        }else if(customerRepository.findCustomerByUsername(username)==null){
            error="No customer found";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        Bill bill=new Bill();
        bill.setBillNo(billNo);
        bill.setCustomer(customerRepository.findCustomerByUsername(username));
        bill.setAmount(amount);
        bill.setIsPaid(false);
        billRepository.save(bill);
        return bill;
    }

    /**
     * get list of bills with given username
     * @param username
     * @return list of bills
     * @author Zhiwei Li
     */
    @Transactional
    public List<Bill> getBillsByCustomer(String username){
        return  billRepository.findBillsByCustomer(customerRepository.findCustomerByUsername(username));
    }

    /**
     * get bill with input bill number
     * @param billNo
     * @return bill
     * @author Zhiwei Li
     * @throws IllegalArgumentException
     */
    @Transactional
    public Bill getBill(int billNo) throws IllegalArgumentException{
        String error = "";
        if (billRepository.findBillByBillNo(billNo)==null) {
            error = "The bill cannot be found";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        return billRepository.findBillByBillNo(billNo);
    }

    /**
     * pay the bill with input billNo
     * @param billNo
     * @return
     * @author Zhiwei Li
     */
    @Transactional
    public Bill payBill(int billNo){
        String error = "";
        Bill bill=billRepository.findBillByBillNo(billNo);
        if (bill==null) {
            error = "The bill cannot be found";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        bill.setIsPaid(true);
        billRepository.save(bill);
        return  bill;
    }

}
