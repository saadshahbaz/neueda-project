/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;

import javax.persistence.*;

// line 41 "../../../../../ARMS.ump"
@Entity
public class Bill
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bill Attributes
  private int amount;
  
  private int billNo;

  public void setBillNo(int value) {
  this.billNo = value;
      }
  @Id
  public int getBillNo() {
  return this.billNo;
         }

  //Bill Associations
  private Customer customer;
  @ManyToOne(cascade = {CascadeType.ALL})
  public Customer getCustomer() {
     return this.customer;
  }
  private ARMS arms;

  @ManyToOne(optional=false)
  public ARMS getArms() {
     return this.arms;
  }

  public void setArms(ARMS arms) {
     this.arms = arms;
  }

  //------------------------
  // CONSTRUCTOR
  //------------------------
/*
  public Bill(int aAmount, Customer aCustomer, ARMS aARMS)
  {
    amount = aAmount;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create bill due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddARMS = setARMS(aARMS);
    if (!didAddARMS)
    {
      throw new RuntimeException("Unable to create bill due to aRMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }*/

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAmount(int aAmount)
  {
    boolean wasSet = false;
    amount = aAmount;
    wasSet = true;
    return wasSet;
  }

  public int getAmount()
  {
    return amount;
  }
  

  
  public void setCustomer(Customer customer) {
	   this.customer = customer;
	}
  /*
  public void delete()
  {
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeBill(this);
    }
    ARMS placeholderARMS = arms;
    this.arms = null;
    if(placeholderARMS != null)
    {
      placeholderARMS.removeBill(this);
    }
  }
*/

  public String toString()
  {
    return super.toString() + "["+
            "amount" + ":" + getAmount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "aRMS = "+(getArms()!=null?Integer.toHexString(System.identityHashCode(getArms())):"null");
  }
}