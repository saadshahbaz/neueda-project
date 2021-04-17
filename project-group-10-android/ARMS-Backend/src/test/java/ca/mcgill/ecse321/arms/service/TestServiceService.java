package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.ArmsApplication;
import ca.mcgill.ecse321.arms.dao.*;
import ca.mcgill.ecse321.arms.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

@ExtendWith(MockitoExtension.class)
public class TestServiceService {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private ServiceService serviceService;

    private static final String SERVICENAME = "TestService";
    private static final int DURATION = 30;
    private static final int PRICE = 100;
    private static final String SERVICENAME2 = "TestService2";
    private static final int DURATION2 = 30;
    private static final int PRICE2 = 60;
    private static final String SERVICENAME3 = "TestService3";
    private static final int DURATION3 = 30;
    private static final int PRICE3 = 150;

    @BeforeEach
    public void setMockOutput() {

        // mock for findServiceByName
        lenient().when(serviceRepository.findServiceByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(SERVICENAME)) {
                Service service = new Service();
                service.setName(SERVICENAME);
                service.setDuration(DURATION);
                service.setPrice(PRICE);
                return service;
            } else {
                return null;
            }
        });

        // mock for findAll
        lenient().when(serviceRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Service service = new Service();
            service.setName(SERVICENAME);
            service.setDuration(DURATION);
            service.setPrice(PRICE);
            Service service2 = new Service();
            service2.setName(SERVICENAME2);
            service2.setDuration(DURATION2);
            service2.setPrice(PRICE2);
            Service service3 = new Service();
            service3.setName(SERVICENAME3);
            service3.setDuration(DURATION3);
            service3.setPrice(PRICE3);
            List<Service> list = new ArrayList<Service>();
            list.add(service);
            list.add(service2);
            list.add(service3);
            return list;
        });

        lenient().when(appointmentRepository.findAppointmentsByService(any(Service.class))).thenAnswer((InvocationOnMock invocation) -> {
            Service service = new Service();
            service.setName(SERVICENAME);
            service.setDuration(DURATION);
            service.setPrice(PRICE);

            Customer customer = createCustomer("2021-03-01", "testName", "testPassword",
                    "test@mail.ca", "88888888");
            Car car = createCar(customer, "testModel", "testManu", "testPlate", "2021");
            Technician technician = createTechnician(0, "testName", "testTechnician@mail.ca");
            Space space = createSpace(0);
            TimeSlot timeSlot = createTimeSlot(technician, space, "2021-03-01", "9:00", "2021-03-01", "10:00");
            Appointment appointment = createAppointment(0, car, service, timeSlot);

            List<Appointment> list = new ArrayList<Appointment>();
            list.add(appointment);
            return list;
        });
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        // mock for save
        lenient().when(serviceRepository.save(any(Service.class))).thenAnswer(returnParam);
    }

    @AfterEach
    public void clearDataBase(){
        serviceRepository.deleteAll();
    }

    @Test
    public void test_create_a_service_successfully(){
        String name = "car wash";
        int duration = 100;
        int price = 30;

        // initialize account to null, so we can see if service creation was successful
        Service service = null;

        try{
            service = serviceService.createService(name, duration, price);
        }catch (IllegalArgumentException e){
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(service);
        assertEquals(name, service.getName());
        assertEquals(duration, service.getDuration());
        assertEquals(price, service.getPrice());

    }

    @Test
    public void test_create_a_service_without_name(){
        String name = "";
        int duration = 100;
        int price = 30;

        // initialize account to null, so we can see if service creation was successful
        Service service = null;

        String error = "";

        try{
            service = serviceService.createService(name, duration, price);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(service);
        assertEquals(error, "You must enter a service name\n");
    }

    @Test
    public void test_create_a_service_with_negative_duration(){
        String name = "car wash";
        int duration = -10;
        int price = 30;

        // initialize account to null, so we can see if service creation was successful
        Service service = null;

        String error = "";

        try{
            service = serviceService.createService(name, duration, price);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(service);
        assertEquals(error, "Duration must be positive\n");
    }

    @Test
    public void test_create_a_service_with_negative_price(){
        String name = "car wash";
        int duration = 100;
        int price = -30;

        // initialize account to null, so we can see if service creation was successful
        Service service = null;

        String error = "";

        try{
            service = serviceService.createService(name, duration, price);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(service);
        assertEquals(error, "Price must be positive");
    }

    @Test
    public void test_create_a_service_that_already_exists(){
        //Service service0 = serviceService.createService("tire change", 30, 100);
        String name = "TestService";
        int duration = 30;
        int price = 100;

        // initialize account to null, so we can see if service creation was successful
        Service service = null;

        String error = "";

        try{
            service = serviceService.createService(name, duration, price);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(service);
        assertEquals(error, "service " + name + " already exists");
    }

    @Test
    public void test_update_a_service_successfully(){
        ArmsApplication.setSystemDateAndTime();
        Service service = null;
        try {
            service = serviceService.updateService("TestService", 50, 60);
        }catch(IllegalArgumentException e){
            fail();
        }

        assertNotNull(service);
        assertEquals(service.getName(), "TestService");
        assertEquals(service.getDuration(), 50);
        assertEquals(service.getPrice(), 60);
    }

    @Test
    public void test_update_a_service_without_specifying_name(){
        String name = "";
        int duration = 50;
        int price = 30;

        Service service =  null;
        String error = "";

        try{
            service = serviceService.updateService(name, duration, price);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(service);
        assertEquals(error, "You must enter a service name\n");
    }

    @Test
    public void test_update_a_service_with_negative_duration(){
        String name = "TestService";
        int duration = -50;
        int price = 30;

        Service service =  null;
        String error = "";

        try{
            service = serviceService.updateService(name, duration, price);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(service);
        assertEquals(error, "Duration must be positive\n");
    }

    @Test
    public void test_update_a_service_with_negative_price(){
        String name = "TestService";
        int duration = 50;
        int price = -30;

        Service service =  null;
        String error = "";

        try{
            service = serviceService.updateService(name, duration, price);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(service);
        assertEquals(error, "Price must be positive");
    }

    @Test
    public void test_update_a_service_does_not_exist(){
        String name = "car wash";
        int duration = 50;
        int price = 30;

        Service service =  null;
        String error = "";

        try{
            service = serviceService.updateService(name, duration, price);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(service);
        assertEquals(error, "No service was found");
    }

    @Test
    public void test_update_a_service_with_future_appointments(){
        ArmsApplication.setCurrentDate(stringToDate("2021-01-01"));
        String name = "TestService";
        int duration = 50;
        int price = 100;

        Service service = null;
        String error = "";

        try{
            service = serviceService.updateService(name, duration, price);
        }catch (IllegalArgumentException e){
            error =  e.getMessage();
        }

        assertNull(service);
        assertEquals(error, "You can not update a service contains future appointments");
    }

    @Test
    public void test_delete_a_service_successfully(){
        ArmsApplication.setSystemDateAndTime();
        String name = "TestService";

        String error = "";

        try{
            serviceService.deleteService(name);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertEquals(error, "");
    }

    @Test
    public void test_delete_a_service_without_specifying_name(){
        String name = "";

        String error = "";

        try{
            serviceService.deleteService(name);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertEquals(error, "You must enter a service name");
    }

    @Test
    public void test_delete_a_service_does_not_exist(){
        String name = "car wash";

        String error = "";

        try{
            serviceService.deleteService(name);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertEquals(error, "No service was found");
    }

    @Test
    public void test_delete_a_service_with_future_appointments(){
        ArmsApplication.setCurrentDate(stringToDate("2021-01-01"));
        String name = "TestService";

        String error = "";

        try{
            serviceService.deleteService(name);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertEquals(error, "You can not delete a service contains future appointments");
    }

    @Test
    public void test_get_existing_service(){
        Service service = serviceService.getService("TestService");
        assertEquals(SERVICENAME, service.getName());
    }

    @Test
    public void test_get_non_existing_service(){
        String error = "";
        try{
            assertNull(serviceService.getService("car wash"));
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "Service " + "car wash" + " does not exist");
    }

    @Test
    public void test_get_all_services(){
        List<ca.mcgill.ecse321.arms.model.Service> services = serviceService.getAllServices();
        assertEquals(services.get(0).getName(), SERVICENAME);
        assertEquals(services.get(1).getName(), SERVICENAME2);
        assertEquals(services.get(2).getName(), SERVICENAME3);
    }

    @Test
    public void test_sort_services_by_price_low_to_high(){
        List<ca.mcgill.ecse321.arms.model.Service> services = serviceService.sortServicesByPriceLtoH();
        assertEquals(services.get(0).getName(), SERVICENAME2);
        assertEquals(services.get(1).getName(), SERVICENAME);
        assertEquals(services.get(2).getName(), SERVICENAME3);
    }

    @Test
    public void test_sort_services_by_price_high_to_low(){
        List<ca.mcgill.ecse321.arms.model.Service> services = serviceService.sortServicesByPriceHtoL();
        assertEquals(services.get(0).getName(), SERVICENAME3);
        assertEquals(services.get(1).getName(), SERVICENAME);
        assertEquals(services.get(2).getName(), SERVICENAME2);
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

