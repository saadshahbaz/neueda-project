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
public class TestCarService {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    @InjectMocks
    private CarService carService;


    private static final String TEST_Username = "TestUsername";
    private static final String TEST_Password = "Testpassword";
    private static final String Test_Email ="672202408@qq.com";
    private static final String Test_phone ="12345678";

    private static final String TEST_Username1 = "TestUsername1";
    private static final String TEST_Password1 = "Testpassword1";
    private static final String Test_Email1 ="6722024081@qq.com";
    private static final String Test_phone1 ="123456781";

    private static final String Test_manufacturer="abc";
    private static final String Test_model = "AB";
    private static final String Test_year="1948";
    private static final String Test_plateN = "A123B4";

    private static final String Test_manufacturer1="abcd";
    private static final String Test_model1 = "ABC";
    private static final String Test_year1="1941";
    private static final String Test_plateN1 = "A123B9";
    @BeforeEach
    public void setMockOutput() {

        // mock for findCarByplateNo
        lenient().when(carRepository.findCarByPlateNo(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(Test_plateN)) {
                Customer customer = new Customer();
                customer.setPassword(TEST_Password);
                customer.setUsername(TEST_Username);
                customer.setEmail(Test_Email);
                customer.setPhoneNumber(Test_phone);
                Car car = new Car();
                car.setModel(Test_model);
                car.setYear(Test_year);
                car.setManufacturer(Test_manufacturer);
                car.setPlateNo(Test_plateN);
                car.setCustomer(customer);
                return car;
            }else if (invocation.getArgument(0).equals(Test_plateN1)) {
                Customer customer = new Customer();
                customer.setPassword(TEST_Password1);
                customer.setUsername(TEST_Username1);
                customer.setEmail(Test_Email1);
                customer.setPhoneNumber(Test_phone1);
                Car car = new Car();
                car.setModel(Test_model1);
                car.setYear(Test_year1);
                car.setManufacturer(Test_manufacturer1);
                car.setPlateNo(Test_plateN1);
                car.setCustomer(customer);
                return car;
            } else {
                return null;
            }
        });
        // mock for deleteCarByplateNo
        lenient().when(carRepository.deleteCarByPlateNo(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(Test_plateN)) {
                return 1;
            } else {
                return 0;
            }
        });

        // mock for findCustomerByUsername
        lenient().when(customerRepository.findCustomerByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TEST_Username)) {
                Customer customer = new Customer();
                customer.setPassword(TEST_Password);
                customer.setUsername(TEST_Username);
                customer.setEmail(Test_Email);
                customer.setPhoneNumber(Test_phone);
                return customer;
            }else if (invocation.getArgument(0).equals(TEST_Username1)) {
                Customer customer = new Customer();
                customer.setPassword(TEST_Password1);
                customer.setUsername(TEST_Username1);
                customer.setEmail(Test_Email1);
                customer.setPhoneNumber(Test_phone1);
                return customer;
            }  else {
                return null;
            }
        });

        lenient().when(carRepository.findCarsByCustomer(any(Customer.class))).thenAnswer((InvocationOnMock invocation) -> {
            //case1, customer with paid cars
            if (((Customer) invocation.getArgument(0)).getUsername().equals(TEST_Username)) {
                ArrayList<Car> cars=new ArrayList<>() ;
                Car car1=new Car();
                car1.setCustomer(((Customer) invocation.getArgument(0)));
                car1.setModel(Test_model);
                car1.setYear(Test_year);
                car1.setManufacturer(Test_manufacturer);
                car1.setPlateNo(Test_plateN);
                cars.add(car1);
                return cars;
            }
             else {
                return null;
            }
        }
        );

        lenient().when(appointmentRepository.findAppointmentByCar(any(Car.class))).thenAnswer((InvocationOnMock invocation) -> {
                    //case1, customer with paid cars
                    if (((Car) invocation.getArgument(0)).getPlateNo().equals(Test_plateN1)) {
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
        //mock up for save
        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(carRepository.save(any(Car.class))).thenAnswer(returnParam);
    }
    @AfterEach
    public void clearDataBase(){
        carRepository.deleteAll();
    }
    @Test
    public void test_create_a_car_successfully() {
        String manufacturer="abc";
        String model = "AB";
        String year="1948";
        String plateN = "A123B5";
        String username=TEST_Username;
        // initialize car to null, so we can see if account creation was successful
        Car car = null;

        try {
            car = carService.createCar(username,manufacturer,model,year,plateN);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(car);
        assertEquals(username, car.getCustomer().getUsername());
        assertEquals(manufacturer,car.getManufacturer());
        assertEquals(model,car.getModel());
        assertEquals(year,car.getYear());
        assertEquals(plateN,car.getPlateNo());
    }
    @Test
    public void test_create_a_car_with_none_existed_username() {
        String manufacturer="abc";
        String model = "AB";
        String year="1948";
        String plateN = "A123B5";
        String username="username1";
        // initialize car to null, so we can see if account creation was successful
        Car car = null;
        String error="";
        try {
            car = carService.createCar(username,manufacturer,model,year,plateN);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(car);
        assertEquals("The customer does not exist", error);
    }
    @Test
    public void test_create_a_car_with_duplicate_plateNo() {
        String manufacturer="abc";
        String model = "AB";
        String year="1948";
        String plateN = "A123B4";
        String username=TEST_Username;

        // initialize account to null, so we can see if account creation was successful
        Car car = null;
        String error="";
        try {
            car = carService.createCar(username,manufacturer,model,year,plateN);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(car);
        assertEquals("The car is already existed", error);
    }

    @Test
    public void test_create_a_car_with_null_parameters() {
        String manufacturer="abc";
        String model = null;
        String year="1948";
        String plateN = "A123B5";
        String username=TEST_Username;

        // initialize account to null, so we can see if account creation was successful
        Car car = null;
        String error="";
        try {
            car = carService.createCar(username,manufacturer,model,year,plateN);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(car);
        assertEquals("The parameter cannot be empty", error);
    }

    @Test
    public void test_get_a_car_by_plateNo_successfully() {
        String plateN = "A123B4";
        // initialize account to null, so we can see if account creation was successful
        Car car = null;
        String error="";
        try {
            car = carService.getCar(plateN);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(car);
        assertEquals(Test_manufacturer,car.getManufacturer());
        assertEquals(Test_model,car.getModel());
        assertEquals(Test_year,car.getYear());
        assertEquals(Test_plateN,car.getPlateNo());
    }
    @Test
    public void test_get_a_car_by_carNp_with_none_exsisted_plateNo() {
        String plateN = "A123B5";
        Car car = null;
        String error="";
        try {
            car = carService.getCar(plateN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(car);
        assertEquals("The car is not founded, check the plate number.", error);
    }
    @Test
    public void test_get_cars_by_customer_successfully() {
        List<Car> cars=new ArrayList<>();
        String username=TEST_Username;
        String error = "";
        Car car = null;
        try {
            cars = carService.getCarsByCustomer(username);
            car = cars.get(0);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(car);
        assertEquals(Test_manufacturer,car.getManufacturer());
        assertEquals(Test_model,car.getModel());
        assertEquals(Test_year,car.getYear());
        assertEquals(Test_plateN,car.getPlateNo());

    }
    @Test
    public void test_get_cars_by_customer_with_none_exsisted_username() {
        List<Car> cars=new ArrayList<>();
        String username="username";
        String error = "";
        Car car = null;
        try {
            cars = carService.getCarsByCustomer(username);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertEquals(0,cars.size());
    }
    @Test
    public void test_update_a_car_successfully() {
        String manufacturer="abcd";
        String model = "ABC";
        String year="1949";
        String plateN = "A123B4";
        String username=TEST_Username;
        // initialize car to null, so we can see if account creation was successful
        Car car = null;

        try {
            car = carService.updateCar(username,manufacturer,model,year,plateN);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(car);
        assertEquals(username, car.getCustomer().getUsername());
        assertEquals(manufacturer,car.getManufacturer());
        assertEquals(model,car.getModel());
        assertEquals(year,car.getYear());
        assertEquals(plateN,car.getPlateNo());
    }
    @Test
    public void test_update_a_car_with_none_existed_plateNo() {
        String manufacturer="abcd";
        String model = "ABC";
        String year="1949";
        String plateN = "A123B5";
        String username=TEST_Username;
        // initialize car to null, so we can see if account creation was successful
        Car car = null;
        String error ="";
        try {
            car = carService.updateCar(username,manufacturer,model,year,plateN);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(car);
        assertEquals("The car does not exist",error);
    }
    @Test
    public void test_update_a_car_with_null_parameter() {
        String manufacturer="abcd";
        String model = null;
        String year="1949";
        String plateN = "A123B4";
        String username=TEST_Username;
        // initialize car to null, so we can see if account creation was successful
        Car car = null;
        String error ="";
        try {
            car = carService.updateCar(username,manufacturer,model,year,plateN);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(car);
        assertEquals("The parameter cannot be empty",error);
    }
    @Test
    public void test_update_a_car_with_none_existed_customer() {
        String manufacturer="abcd";
        String model = "ABC";
        String year="1949";
        String plateN = "A123B4";
        String username="username";
        // initialize car to null, so we can see if account creation was successful
        Car car = null;
        String error ="";
        try {
            car = carService.updateCar(username,manufacturer,model,year,plateN);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(car);
        assertEquals("The customer does not exist",error);
    }

    @Test
    public void test_delete_a_car_successfully(){
        String plateNo = Test_plateN;

        // initialize account to null, so we can see if account creation was successful
        Integer i = null;
        String error="";
        try {
            i = carService.deleteCar(plateNo);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertEquals(1,i);
    }
    @Test
    public void test_delete_a_car_with_appointment(){
        String plateNo = Test_plateN1;

        // initialize account to null, so we can see if account creation was successful
        Integer i = 0;
        String error="";
        try {
            i = carService.deleteCar(plateNo);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertEquals(0,i);
        assertEquals("The car has appointments, not allowed to delete",error);
    }
    @Test
    public void test_delete_a_car_with_null(){
        String plateNo = "a";
        // initialize account to null, so we can see if account creation was successful
        Integer i = 0;
        String error="";
        try {
            i = carService.deleteCar(plateNo);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertEquals(0,i);
        assertEquals(error,"The plateNo does not exist");
    }



}