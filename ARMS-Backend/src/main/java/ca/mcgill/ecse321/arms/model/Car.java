package ca.mcgill.ecse321.arms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Car {
    private String plateNo;

    public void setPlateNo(String value) {
        this.plateNo = value;
    }

    @Id
    public String getPlateNo() {
        return this.plateNo;
    }

    private String model;

    public void setModel(String value) {
        this.model = value;
    }

    public String getModel() {
        return this.model;
    }

    private String manufacturer;

    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    private String year;

    public void setYear(String value) {
        this.year = value;
    }

    public String getYear() {
        return this.year;
    }

    private Customer customer;

    @ManyToOne(optional = false)
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Set<Appointment> appointment;

    @OneToMany(mappedBy = "car")
    public Set<Appointment> getAppointment() {
        return this.appointment;
    }

    public void setAppointment(Set<Appointment> appointments) {
        this.appointment = appointments;
    }

}
