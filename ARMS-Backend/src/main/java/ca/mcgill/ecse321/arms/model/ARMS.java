/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.arms.model;
import java.util.*;
import java.sql.Time;
import java.sql.Date;

// line 3 "../../../../../ARMS.ump"
public class ARMS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ARMS Associations
  private Business business;
  private Assistant assistant;
  private List<Customer> customers;
  private List<BusinessHour> hours;
  private List<Appointment> appointments;
  private List<TimeSlot> timeSlots;
  private List<Technician> technicians;
  private List<Space> space;
  private List<Car> cars;
  private List<Service> services;
  private List<Bill> bills;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ARMS()
  {
    customers = new ArrayList<Customer>();
    hours = new ArrayList<BusinessHour>();
    appointments = new ArrayList<Appointment>();
    timeSlots = new ArrayList<TimeSlot>();
    technicians = new ArrayList<Technician>();
    space = new ArrayList<Space>();
    cars = new ArrayList<Car>();
    services = new ArrayList<Service>();
    bills = new ArrayList<Bill>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Business getBusiness()
  {
    return business;
  }

  public boolean hasBusiness()
  {
    boolean has = business != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Assistant getAssistant()
  {
    return assistant;
  }

  public boolean hasAssistant()
  {
    boolean has = assistant != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Customer getCustomer(int index)
  {
    Customer aCustomer = customers.get(index);
    return aCustomer;
  }

  public List<Customer> getCustomers()
  {
    List<Customer> newCustomers = Collections.unmodifiableList(customers);
    return newCustomers;
  }

  public int numberOfCustomers()
  {
    int number = customers.size();
    return number;
  }

  public boolean hasCustomers()
  {
    boolean has = customers.size() > 0;
    return has;
  }

  public int indexOfCustomer(Customer aCustomer)
  {
    int index = customers.indexOf(aCustomer);
    return index;
  }
  /* Code from template association_GetMany */
  public BusinessHour getHour(int index)
  {
    BusinessHour aHour = hours.get(index);
    return aHour;
  }

  public List<BusinessHour> getHours()
  {
    List<BusinessHour> newHours = Collections.unmodifiableList(hours);
    return newHours;
  }

  public int numberOfHours()
  {
    int number = hours.size();
    return number;
  }

  public boolean hasHours()
  {
    boolean has = hours.size() > 0;
    return has;
  }

  public int indexOfHour(BusinessHour aHour)
  {
    int index = hours.indexOf(aHour);
    return index;
  }
  /* Code from template association_GetMany */
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointments.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }

  public int numberOfAppointments()
  {
    int number = appointments.size();
    return number;
  }

  public boolean hasAppointments()
  {
    boolean has = appointments.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointments.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_GetMany */
  public TimeSlot getTimeSlot(int index)
  {
    TimeSlot aTimeSlot = timeSlots.get(index);
    return aTimeSlot;
  }

  public List<TimeSlot> getTimeSlots()
  {
    List<TimeSlot> newTimeSlots = Collections.unmodifiableList(timeSlots);
    return newTimeSlots;
  }

  public int numberOfTimeSlots()
  {
    int number = timeSlots.size();
    return number;
  }

  public boolean hasTimeSlots()
  {
    boolean has = timeSlots.size() > 0;
    return has;
  }

  public int indexOfTimeSlot(TimeSlot aTimeSlot)
  {
    int index = timeSlots.indexOf(aTimeSlot);
    return index;
  }
  /* Code from template association_GetMany */
  public Technician getTechnician(int index)
  {
    Technician aTechnician = technicians.get(index);
    return aTechnician;
  }

  public List<Technician> getTechnicians()
  {
    List<Technician> newTechnicians = Collections.unmodifiableList(technicians);
    return newTechnicians;
  }

  public int numberOfTechnicians()
  {
    int number = technicians.size();
    return number;
  }

  public boolean hasTechnicians()
  {
    boolean has = technicians.size() > 0;
    return has;
  }

  public int indexOfTechnician(Technician aTechnician)
  {
    int index = technicians.indexOf(aTechnician);
    return index;
  }
  /* Code from template association_GetMany */
  public Space getSpace(int index)
  {
    Space aSpace = space.get(index);
    return aSpace;
  }

  public List<Space> getSpace()
  {
    List<Space> newSpace = Collections.unmodifiableList(space);
    return newSpace;
  }

  public int numberOfSpace()
  {
    int number = space.size();
    return number;
  }

  public boolean hasSpace()
  {
    boolean has = space.size() > 0;
    return has;
  }

  public int indexOfSpace(Space aSpace)
  {
    int index = space.indexOf(aSpace);
    return index;
  }
  /* Code from template association_GetMany */
  public Car getCar(int index)
  {
    Car aCar = cars.get(index);
    return aCar;
  }

  public List<Car> getCars()
  {
    List<Car> newCars = Collections.unmodifiableList(cars);
    return newCars;
  }

  public int numberOfCars()
  {
    int number = cars.size();
    return number;
  }

  public boolean hasCars()
  {
    boolean has = cars.size() > 0;
    return has;
  }

  public int indexOfCar(Car aCar)
  {
    int index = cars.indexOf(aCar);
    return index;
  }
  /* Code from template association_GetMany */
  public Service getService(int index)
  {
    Service aService = services.get(index);
    return aService;
  }

  public List<Service> getServices()
  {
    List<Service> newServices = Collections.unmodifiableList(services);
    return newServices;
  }

  public int numberOfServices()
  {
    int number = services.size();
    return number;
  }

  public boolean hasServices()
  {
    boolean has = services.size() > 0;
    return has;
  }

  public int indexOfService(Service aService)
  {
    int index = services.indexOf(aService);
    return index;
  }
  /* Code from template association_GetMany */
  public Bill getBill(int index)
  {
    Bill aBill = bills.get(index);
    return aBill;
  }

  public List<Bill> getBills()
  {
    List<Bill> newBills = Collections.unmodifiableList(bills);
    return newBills;
  }

  public int numberOfBills()
  {
    int number = bills.size();
    return number;
  }

  public boolean hasBills()
  {
    boolean has = bills.size() > 0;
    return has;
  }

  public int indexOfBill(Bill aBill)
  {
    int index = bills.indexOf(aBill);
    return index;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setBusiness(Business aNewBusiness)
  {
    boolean wasSet = false;
    if (business != null && !business.equals(aNewBusiness) && equals(business.getARMS()))
    {
      //Unable to setBusiness, as existing business would become an orphan
      return wasSet;
    }

    business = aNewBusiness;
    ARMS anOldARMS = aNewBusiness != null ? aNewBusiness.getARMS() : null;

    if (!this.equals(anOldARMS))
    {
      if (anOldARMS != null)
      {
        anOldARMS.business = null;
      }
      if (business != null)
      {
        business.setARMS(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setAssistant(Assistant aNewAssistant)
  {
    boolean wasSet = false;
    if (assistant != null && !assistant.equals(aNewAssistant) && equals(assistant.getARMS()))
    {
      //Unable to setAssistant, as existing assistant would become an orphan
      return wasSet;
    }

    assistant = aNewAssistant;
    ARMS anOldARMS = aNewAssistant != null ? aNewAssistant.getARMS() : null;

    if (!this.equals(anOldARMS))
    {
      if (anOldARMS != null)
      {
        anOldARMS.assistant = null;
      }
      if (assistant != null)
      {
        assistant.setARMS(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCustomers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Customer addCustomer(String aUsername, String aPassword)
  {
    return new Customer(aUsername, aPassword, this);
  }

  public boolean addCustomer(Customer aCustomer)
  {
    boolean wasAdded = false;
    if (customers.contains(aCustomer)) { return false; }
    ARMS existingARMS = aCustomer.getARMS();
    boolean isNewARMS = existingARMS != null && !this.equals(existingARMS);
    if (isNewARMS)
    {
      aCustomer.setARMS(this);
    }
    else
    {
      customers.add(aCustomer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCustomer(Customer aCustomer)
  {
    boolean wasRemoved = false;
    //Unable to remove aCustomer, as it must always have a aRMS
    if (!this.equals(aCustomer.getARMS()))
    {
      customers.remove(aCustomer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCustomerAt(Customer aCustomer, int index)
  {  
    boolean wasAdded = false;
    if(addCustomer(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCustomerAt(Customer aCustomer, int index)
  {
    boolean wasAdded = false;
    if(customers.contains(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCustomerAt(aCustomer, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BusinessHour addHour(BusinessHour.DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime)
  {
    return new BusinessHour(aDayOfWeek, aStartTime, aEndTime, this);
  }

  public boolean addHour(BusinessHour aHour)
  {
    boolean wasAdded = false;
    if (hours.contains(aHour)) { return false; }
    ARMS existingARMS = aHour.getARMS();
    boolean isNewARMS = existingARMS != null && !this.equals(existingARMS);
    if (isNewARMS)
    {
      aHour.setARMS(this);
    }
    else
    {
      hours.add(aHour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHour(BusinessHour aHour)
  {
    boolean wasRemoved = false;
    //Unable to remove aHour, as it must always have a aRMS
    if (!this.equals(aHour.getARMS()))
    {
      hours.remove(aHour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHourAt(BusinessHour aHour, int index)
  {  
    boolean wasAdded = false;
    if(addHour(aHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHours()) { index = numberOfHours() - 1; }
      hours.remove(aHour);
      hours.add(index, aHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHourAt(BusinessHour aHour, int index)
  {
    boolean wasAdded = false;
    if(hours.contains(aHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHours()) { index = numberOfHours() - 1; }
      hours.remove(aHour);
      hours.add(index, aHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHourAt(aHour, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(Car aCar, Service aServices, TimeSlot aTimeSlot)
  {
    return new Appointment(aCar, aServices, aTimeSlot, this);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    ARMS existingARMS = aAppointment.getARMS();
    boolean isNewARMS = existingARMS != null && !this.equals(existingARMS);
    if (isNewARMS)
    {
      aAppointment.setARMS(this);
    }
    else
    {
      appointments.add(aAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAppointment, as it must always have a aRMS
    if (!this.equals(aAppointment.getARMS()))
    {
      appointments.remove(aAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointments.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addTimeSlot(Date aStartDate, Time aStartTime, Date aEndDate, Time aEndTime)
  {
    return new TimeSlot(aStartDate, aStartTime, aEndDate, aEndTime, this);
  }

  public boolean addTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasAdded = false;
    if (timeSlots.contains(aTimeSlot)) { return false; }
    ARMS existingARMS = aTimeSlot.getARMS();
    boolean isNewARMS = existingARMS != null && !this.equals(existingARMS);
    if (isNewARMS)
    {
      aTimeSlot.setARMS(this);
    }
    else
    {
      timeSlots.add(aTimeSlot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasRemoved = false;
    //Unable to remove aTimeSlot, as it must always have a aRMS
    if (!this.equals(aTimeSlot.getARMS()))
    {
      timeSlots.remove(aTimeSlot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTimeSlotAt(TimeSlot aTimeSlot, int index)
  {  
    boolean wasAdded = false;
    if(addTimeSlot(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeSlotAt(TimeSlot aTimeSlot, int index)
  {
    boolean wasAdded = false;
    if(timeSlots.contains(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeSlotAt(aTimeSlot, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTechnicians()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Technician addTechnician(String aName)
  {
    return new Technician(aName, this);
  }

  public boolean addTechnician(Technician aTechnician)
  {
    boolean wasAdded = false;
    if (technicians.contains(aTechnician)) { return false; }
    ARMS existingARMS = aTechnician.getARMS();
    boolean isNewARMS = existingARMS != null && !this.equals(existingARMS);
    if (isNewARMS)
    {
      aTechnician.setARMS(this);
    }
    else
    {
      technicians.add(aTechnician);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTechnician(Technician aTechnician)
  {
    boolean wasRemoved = false;
    //Unable to remove aTechnician, as it must always have a aRMS
    if (!this.equals(aTechnician.getARMS()))
    {
      technicians.remove(aTechnician);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTechnicianAt(Technician aTechnician, int index)
  {  
    boolean wasAdded = false;
    if(addTechnician(aTechnician))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTechnicians()) { index = numberOfTechnicians() - 1; }
      technicians.remove(aTechnician);
      technicians.add(index, aTechnician);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTechnicianAt(Technician aTechnician, int index)
  {
    boolean wasAdded = false;
    if(technicians.contains(aTechnician))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTechnicians()) { index = numberOfTechnicians() - 1; }
      technicians.remove(aTechnician);
      technicians.add(index, aTechnician);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTechnicianAt(aTechnician, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpace()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Space addSpace()
  {
    return new Space(this);
  }

  public boolean addSpace(Space aSpace)
  {
    boolean wasAdded = false;
    if (space.contains(aSpace)) { return false; }
    ARMS existingARMS = aSpace.getARMS();
    boolean isNewARMS = existingARMS != null && !this.equals(existingARMS);
    if (isNewARMS)
    {
      aSpace.setARMS(this);
    }
    else
    {
      space.add(aSpace);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpace(Space aSpace)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpace, as it must always have a aRMS
    if (!this.equals(aSpace.getARMS()))
    {
      space.remove(aSpace);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpaceAt(Space aSpace, int index)
  {  
    boolean wasAdded = false;
    if(addSpace(aSpace))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpace()) { index = numberOfSpace() - 1; }
      space.remove(aSpace);
      space.add(index, aSpace);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpaceAt(Space aSpace, int index)
  {
    boolean wasAdded = false;
    if(space.contains(aSpace))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpace()) { index = numberOfSpace() - 1; }
      space.remove(aSpace);
      space.add(index, aSpace);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpaceAt(aSpace, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCars()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Car addCar(String aModel, String aManufacturer, String aPlateNo, String aYear, Customer aCustomer)
  {
    return new Car(aModel, aManufacturer, aPlateNo, aYear, aCustomer, this);
  }

  public boolean addCar(Car aCar)
  {
    boolean wasAdded = false;
    if (cars.contains(aCar)) { return false; }
    ARMS existingARMS = aCar.getARMS();
    boolean isNewARMS = existingARMS != null && !this.equals(existingARMS);
    if (isNewARMS)
    {
      aCar.setARMS(this);
    }
    else
    {
      cars.add(aCar);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCar(Car aCar)
  {
    boolean wasRemoved = false;
    //Unable to remove aCar, as it must always have a aRMS
    if (!this.equals(aCar.getARMS()))
    {
      cars.remove(aCar);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCarAt(Car aCar, int index)
  {  
    boolean wasAdded = false;
    if(addCar(aCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCars()) { index = numberOfCars() - 1; }
      cars.remove(aCar);
      cars.add(index, aCar);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCarAt(Car aCar, int index)
  {
    boolean wasAdded = false;
    if(cars.contains(aCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCars()) { index = numberOfCars() - 1; }
      cars.remove(aCar);
      cars.add(index, aCar);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCarAt(aCar, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, int aDuration, int aPrice)
  {
    return new Service(aName, aDuration, aPrice, this);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    ARMS existingARMS = aService.getARMS();
    boolean isNewARMS = existingARMS != null && !this.equals(existingARMS);
    if (isNewARMS)
    {
      aService.setARMS(this);
    }
    else
    {
      services.add(aService);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeService(Service aService)
  {
    boolean wasRemoved = false;
    //Unable to remove aService, as it must always have a aRMS
    if (!this.equals(aService.getARMS()))
    {
      services.remove(aService);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceAt(Service aService, int index)
  {  
    boolean wasAdded = false;
    if(addService(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceAt(Service aService, int index)
  {
    boolean wasAdded = false;
    if(services.contains(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceAt(aService, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBills()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bill addBill(int aAmount, Customer aCustomer)
  {
    return new Bill(aAmount, aCustomer, this);
  }

  public boolean addBill(Bill aBill)
  {
    boolean wasAdded = false;
    if (bills.contains(aBill)) { return false; }
    ARMS existingARMS = aBill.getARMS();
    boolean isNewARMS = existingARMS != null && !this.equals(existingARMS);
    if (isNewARMS)
    {
      aBill.setARMS(this);
    }
    else
    {
      bills.add(aBill);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBill(Bill aBill)
  {
    boolean wasRemoved = false;
    //Unable to remove aBill, as it must always have a aRMS
    if (!this.equals(aBill.getARMS()))
    {
      bills.remove(aBill);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBillAt(Bill aBill, int index)
  {  
    boolean wasAdded = false;
    if(addBill(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBills()) { index = numberOfBills() - 1; }
      bills.remove(aBill);
      bills.add(index, aBill);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBillAt(Bill aBill, int index)
  {
    boolean wasAdded = false;
    if(bills.contains(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBills()) { index = numberOfBills() - 1; }
      bills.remove(aBill);
      bills.add(index, aBill);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBillAt(aBill, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Business existingBusiness = business;
    business = null;
    if (existingBusiness != null)
    {
      existingBusiness.delete();
      existingBusiness.setARMS(null);
    }
    Assistant existingAssistant = assistant;
    assistant = null;
    if (existingAssistant != null)
    {
      existingAssistant.delete();
      existingAssistant.setARMS(null);
    }
    while (customers.size() > 0)
    {
      Customer aCustomer = customers.get(customers.size() - 1);
      aCustomer.delete();
      customers.remove(aCustomer);
    }
    
    while (hours.size() > 0)
    {
      BusinessHour aHour = hours.get(hours.size() - 1);
      aHour.delete();
      hours.remove(aHour);
    }
    
    while (appointments.size() > 0)
    {
      Appointment aAppointment = appointments.get(appointments.size() - 1);
      aAppointment.delete();
      appointments.remove(aAppointment);
    }
    
    while (timeSlots.size() > 0)
    {
      TimeSlot aTimeSlot = timeSlots.get(timeSlots.size() - 1);
      aTimeSlot.delete();
      timeSlots.remove(aTimeSlot);
    }
    
    while (technicians.size() > 0)
    {
      Technician aTechnician = technicians.get(technicians.size() - 1);
      aTechnician.delete();
      technicians.remove(aTechnician);
    }
    
    while (space.size() > 0)
    {
      Space aSpace = space.get(space.size() - 1);
      aSpace.delete();
      space.remove(aSpace);
    }
    
    while (cars.size() > 0)
    {
      Car aCar = cars.get(cars.size() - 1);
      aCar.delete();
      cars.remove(aCar);
    }
    
    while (services.size() > 0)
    {
      Service aService = services.get(services.size() - 1);
      aService.delete();
      services.remove(aService);
    }
    
    while (bills.size() > 0)
    {
      Bill aBill = bills.get(bills.size() - 1);
      aBill.delete();
      bills.remove(aBill);
    }
    
  }

}