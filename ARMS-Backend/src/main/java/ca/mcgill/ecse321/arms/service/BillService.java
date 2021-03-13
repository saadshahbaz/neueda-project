package ca.mcgill.ecse321.arms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.arms.model.*;
import ca.mcgill.ecse321.arms.dao.*;

import java.util.ArrayList;
import java.util.List;
public class BillService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BillRepository billRepository;

    @Transactional
    public Bill createBill(Customer customer, int billNo, int amount) throws IllegalArgumentException{
        String error="";
        if (billRepository.findBillByBillNo(billNo)!=null){
            error="the billNo is already existed";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        Bill bill=new Bill();
        bill.setBillNo(billNo);
        bill.setCustomer(customer);
        bill.setAmount(amount);
        bill.setIsPaid(false);
        billRepository.save(bill);
        return bill;
    }
    @Transactional
    public List<Bill> getBillsByCustomer(Customer customer){
        return  billRepository.findBillsByCustomer(customer);
    }
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
    @Transactional
    public void payBill(int billNo){
        Bill bill=billRepository.findBillByBillNo(billNo);
        bill.setIsPaid(true);
        billRepository.save(bill);
    }



    <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
