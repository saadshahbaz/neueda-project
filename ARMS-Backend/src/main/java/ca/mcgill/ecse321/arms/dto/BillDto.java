package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.Customer;

public class BillDto {
    private int billNo;
    private int amount;
    private boolean isPaid;
    private String username;
    public BillDto(String username,boolean isPaid,int amount,int billNo){
        this.amount=amount;
        this.billNo=billNo;
        this.username=username;
        this.isPaid=isPaid;
    }

    public String getCustomer() {
        return username;
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
