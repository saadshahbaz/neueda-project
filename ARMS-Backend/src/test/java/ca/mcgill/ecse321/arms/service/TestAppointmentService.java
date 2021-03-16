package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.dao.AppointmentRepository;
import ca.mcgill.ecse321.arms.dao.ServiceRepository;
import ca.mcgill.ecse321.arms.model.*;
import ca.mcgill.ecse321.arms.service.ServiceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestAppointmentService {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    //create service parameter
    private static final String SERVICENAME = "TestService";
    private static final int DURATION = 30;
    private static final int PRICE = 100;
    //create car parameter
    private static final String MANUFACTURER="abc";
    private static final String MODEL = "AB";
    private static final String YEAR="1948";
    private static final String PLATENO = "A123B4";
    //create time slot parameter
    private static final String STARTDATE = "2021-03-01";
    private static final String STARTTIME = "9:00";
    private static final String ENDDATE = "2021-03-01";
    private static final String ENDTIME = "10:00";
    //create technician parameter
    private static final int TECHID = 0;
    private static final String TECHNAME = "testName";
    private static final String TECHEMAIL = "testTechnician@mail.ca";
    //create space parameter
    private static final int SPACEID = 0;
    //create customer parameter
    private static final String LASTREMINDER = "2021-03-01";
    private static final String USERNAME = "testName";
    private static final String PASSWORD = "testPassword";
    private static final String EMAIL = "test@mail.ca";
    private static final String PHONENUMBER = "88888888";

    private static final int APPOINTMENTID = 0;

    @BeforeEach
    public void setMockOutput() {

        // mock for findAppointmentByAppointmentID
        lenient().when(appointmentRepository.findAppointmentByAppointmentID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(APPOINTMENTID)) {
                Service service = createService(SERVICENAME,DURATION,PRICE);
                Customer customer = createCustomer(LASTREMINDER, USERNAME, PASSWORD, EMAIL, PHONENUMBER);
                Car car = createCar(customer, MODEL, MANUFACTURER, PLATENO, YEAR);
                Technician technician = createTechnician(TECHID, TECHNAME, TECHEMAIL);
                Space space = createSpace(SPACEID);
                TimeSlot timeSlot = createTimeSlot(technician, space, STARTDATE, STARTTIME, ENDDATE, ENDTIME);
                Appointment appointment = createAppointment(APPOINTMENTID, car, service, timeSlot);
                return appointment;
            } else {
                return null;
            }
        });

        //mock for findAppointmentByCar
        lenient().when(appointmentRepository.findAppointmentByCar(any(Car.class))).thenAnswer((InvocationOnMock invocation) -> {
                    //case1, customer with paid cars
                    if (((Car) invocation.getArgument(0)).getPlateNo().equals(PLATENO)) {
                        Appointment appointment=new Appointment();
                        appointment.setAppointmentID(0);
                        appointment.setCar(((Car) invocation.getArgument(0)));
                        return appointment;
                    }
                    else {
                        return null;
                    }
                }
        );

        //mock for findAppointmentByService
        lenient().when(appointmentRepository.findAppointmentsByService(any(Service.class))).thenAnswer((InvocationOnMock invocation) -> {
            Service service = createService(SERVICENAME,DURATION,PRICE);
            Customer customer = createCustomer(LASTREMINDER, USERNAME, PASSWORD, EMAIL, PHONENUMBER);
            Car car = createCar(customer, MODEL, MANUFACTURER, PLATENO, YEAR);
            Technician technician = createTechnician(TECHID, TECHNAME, TECHEMAIL);
            Space space = createSpace(SPACEID);
            TimeSlot timeSlot = createTimeSlot(technician, space, STARTDATE, STARTTIME, ENDDATE, ENDTIME);
            Appointment appointment = createAppointment(APPOINTMENTID, car, service, timeSlot);

            List<Appointment> list = new ArrayList<>();
            list.add(appointment);
            return list;
        });

        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        // mock for save
        lenient().when(appointmentRepository.save(any(Appointment.class))).thenAnswer(returnParam);

    }

    @AfterEach
    public void clearDataBase(){
        appointmentRepository.deleteAll();
    }

    @Test
    public void test_create_an_appointment_successfully(){
        Service service = createService(SERVICENAME,DURATION,PRICE);
        Customer customer = createCustomer(LASTREMINDER, USERNAME, PASSWORD, EMAIL, PHONENUMBER);
        Car car = createCar(customer, MODEL, MANUFACTURER, PLATENO, YEAR);
        Technician technician = createTechnician(TECHID, TECHNAME, TECHEMAIL);
        Space space = createSpace(SPACEID);
        TimeSlot timeSlot = createTimeSlot(technician, space, STARTDATE, STARTTIME, ENDDATE, ENDTIME);
        // initialize account to null, so we can see if appointment creation was successful
        Appointment appointment = null;

        try{
            appointment = appointmentService.createAppointment(service, car, timeSlot);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(appointment);
        assertEquals(service, appointment.getService());
        assertEquals(car, appointment.getCar());
        assertEquals(timeSlot, appointment.getTimeSlot());

    }


    //below are helper method to create test instance
    public Service createService(String serviceName, int duration, int price){
        Service service = new Service();
        service.setName(serviceName);
        service.setDuration(duration);
        service.setPrice(price);
        return service;
    }

    public Customer createCustomer(String lastReminder, String username, String password,
                                   String email, String phoneNumber){
        Customer customer = new Customer();
        customer.setLastReminder(lastReminder);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        //customerRepository.save(customer);
        return customer;
    }

    public Car createCar(Customer customer, String model, String manu, String plate, String year){
        Car car = new Car();
        car.setCustomer(customer);
        car.setModel(model);
        car.setManufacturer(manu);
        car.setPlateNo(plate);
        car.setYear(year);
        //carRepository.save(car);
        return car;
    }

    public Technician createTechnician(int id, String name, String email){
        Technician technician = new Technician();
        technician.setTechnicianID(id);
        technician.setName(name);
        technician.setEmail(email);
        //technicianRepository.save(technician);
        return technician;
    }

    public Space createSpace(int id){
        Space space = new Space();
        space.setSpaceID(id);
        //spaceRepository.save(space);
        return space;
    }

    public TimeSlot createTimeSlot(Technician technician, Space space, String startDate, String startTime, String endDate, String endTime){
        Date sD = stringToDate(startDate);
        Time sT = stringToTime(startTime);
        Date eD = stringToDate(endDate);
        Time eT = stringToTime(endTime);
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDate(sD);
        timeSlot.setStartTime(sT);
        timeSlot.setEndDate(eD);
        timeSlot.setEndTime(eT);
        timeSlot.setTechnician(technician);
        timeSlot.setSpace(space);
        //timeSlotRepository.save(timeSlot);
        return timeSlot;
    }

    public Appointment createAppointment(int id, Car car, Service service, TimeSlot timeSlot){
        Appointment appointment = new Appointment();
        appointment.setAppointmentID(id);
        appointment.setCar(car);
        appointment.setService(service);
        appointment.setTimeSlot(timeSlot);
        //appointmentRepository.save(appointment);
        return appointment;
    }

    private static Date stringToDate(String string){
        Date date = Date.valueOf(string);
        return date;
    }

    private static Time stringToTime(String string){
        Time st = Time.valueOf(string+":00");
        return st;
    }
}