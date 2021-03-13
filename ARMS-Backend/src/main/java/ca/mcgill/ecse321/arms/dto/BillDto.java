package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.Customer;

public class BillDto {
    private int billNo;
    private int amount;
    private boolean isPaid;
    private Customer customer;
    public BillDto(Customer customer,boolean isPaid,int amount,int billNo){
        this.amount=amount;
        this.billNo=billNo;
        this.customer=customer;
        this.isPaid=isPaid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getBillNo() {
        return billNo;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }
}
