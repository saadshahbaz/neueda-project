package ca.mcgill.ecse321.arms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bill {
    private int billNo;

    public void setBillNo(int value) {
        this.billNo = value;
    }

    @Id
    public int getBillNo() {
        return this.billNo;
    }

    private int amount;

    public void setAmount(int value) {
        this.amount = value;
    }

    public int getAmount() {
        return this.amount;
    }

    private Customer customer;

    @ManyToOne(optional = false)
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private boolean isPaid;

    public void setIsPaid(boolean value) {
        this.isPaid = value;
    }

    public boolean isIsPaid() {
        return this.isPaid;
    }

}