/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bill{
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

    @ManyToOne(optional=false)
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private ARMS ARMS;

    @ManyToOne(optional=false)
    public ARMS getARMS() {
        return this.ARMS;
    }

    public void setARMS(ARMS aRMS) {
        this.ARMS = aRMS;
    }
    private boolean isPaied;

    public void setIsPaied(boolean value) {
        this.isPaied = value;
    }
    public boolean isIsPaied() {
        return this.isPaied;
    }

}