package ca.mcgill.ecse321.arms.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.arms.dao.*;
import ca.mcgill.ecse321.arms.model.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestArmsPersistence {

	// autowired here
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private ArmsRepository armsRepository;
	@Autowired
	private AssistantRepository assistantRepository;
	@Autowired
	private BillRepository billRepository;
	@Autowired
	private BusinessHourRepository businessHourRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private SpaceRepository spaceRepository;
	@Autowired
	private TechnicianRepository technicianRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	@AfterEach
	public void clearDB() {
		appointmentRepository.deleteAll();
		armsRepository.deleteAll();
		assistantRepository.deleteAll();
		billRepository.deleteAll();
		businessHourRepository.deleteAll();
		carRepository.deleteAll();
		customerRepository.deleteAll();
		serviceRepository.deleteAll();
		spaceRepository.deleteAll();
		technicianRepository.deleteAll();
		timeSlotRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	/**
	 * @author Cecilia Jiang
	 */
	public Customer createCustomer() {
		String username = "TestCustomerName";
		String password = "myPassword123!";
		int lastReminder = 20200222;
		
		Customer customer = new Customer();
		customer.setUsername(username);
		customer.setPassword(password);
		customer.setLastReminder(lastReminder);
		
		customerRepository.save(customer);
		return customer;
	}

	/**
	 * @author Cecilia Jiang
	 */
	public Customer createCustomerWithCar() {
		// create customer
		String username = "TestCustomerName";
		String password = "myPassword123!";
		int lastReminder = 20200222;
		Customer customer = new Customer();
		customer.setUsername(username);
		customer.setPassword(password);
		customer.setLastReminder(lastReminder);
		customerRepository.save(customer);
		
		String model = "TestModel";
		String manufacturer = "TestManufacturer";
		String plateNo = "QC99999";
		String year = "2021";
		
		Set<Car> carSet = new HashSet<Car>();
		Car car = new Car();
		car.setModel(model);
		car.setManufacturer(manufacturer);
		car.setPlateNo(plateNo);
		car.setYear(year);
		carSet.add(car);
		
		customer.setCar(carSet);
		return customer;
	}
	
	/**
	 * @author Cecilia Jiang
	 */
	public void createARMS() {
		ARMS sys = new ARMS();
        armsRepository.save(sys);
	}
	
	@Test
	public void testPersistAndLoadCustomer() {

		String username = "TestUserName";
		String password = "TestPassword";
		Customer customer = new Customer();
		ARMS arms = new ARMS();
		armsRepository.save(arms);


    customer.setARMS(arms);
    customer.setUsername(username);
    customer.setPassword(password);
    customerRepository.save(customer);
    String id = customer.getUsername();
    customer = null;
    customer = customerRepository.findCustomerByUsername(id);
    assertNotNull(customer);
    assertEquals(customer.getPassword(), password);
    assertEquals(customer.getUsername(), username);

	}

	/**
	 * @author Cecilia Jiang
	 */
	@Test
	public void testPersistAndLoadService() {
		createARMS();
		String name = "TestServiceName";
		int duration = 60;
		int price = 100;
		
		Service service = new Service();
		service.setName(name);
		service.setDuration(duration);
		service.setPrice(price);
		this.serviceRepository.save(service);
		
		service = null;
		service = serviceRepository.findServiceByName(name);
		
		assertNotNull(service);
        assertEquals(service.getName(), name);
	}


	/**
	 * @author Jianmo Li
	 */
	@Test
	public void testPersistenceAndLoadAssistant() {
		String username = "tester";
		String password = "1234";
		Assistant ass = new Assistant();
		ARMS arms = new ARMS();
		armsRepository.save(arms);

		ass.setARMS(arms);
		ass.setUsername(username);
		ass.setPassword(password);
		assistantRepository.save(ass);

		ass = null;

		ass = assistantRepository.findAssistantByUsername(username);
		assertNotNull(ass);
		assertEquals(ass.getUsername(),username);
		assertEquals(ass.getPassword(),password);

	}

	/**
	 * @author Jianmo Li
	 */
	@Test
	public void testPersistenceAndLoadBill(){

	}
}
