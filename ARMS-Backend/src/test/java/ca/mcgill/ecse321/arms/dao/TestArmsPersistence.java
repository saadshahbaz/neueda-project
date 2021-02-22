package ca.mcgill.ecse321.arms.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	//autowired here
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
		//appointmentRepository.deleteAll();
		assistantRepository.deleteAll();
		//billRepository.deleteAll();
		//businessHourRepository.deleteAll();
		//carRepository.deleteAll();
		customerRepository.deleteAll();
		//serviceRepository.deleteAll();
		//spaceRepository.deleteAll();
		//technicianRepository.deleteAll();
		//timeSlotRepository.deleteAll();
		userRepository.deleteAll();
		armsRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadCustomer() {
		ARMS arms = new ARMS();
		arms.setArmsID(1);
		String username="TestUserName";
		String password="TestPassword";
		Customer customer = new Customer();
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
	 * @author Jianmo Li
	 */
	@Test
	public void testPersistenceAndLoadAssistant() {
		String username = "tester";
		String password = "1234";
		Assistant ass = new Assistant();
		ARMS arms = new ARMS();
		arms.setArmsID(2);
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

}