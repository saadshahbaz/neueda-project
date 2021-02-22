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
	
	@Test
    public void testPersistAndLoadCustomer() {

		String username="TestUserName";
        String password="TestPassword";
        Customer customer = new Customer();
        ARMS arms = new ARMS();
        armsRepository.save(arms);

        customer.setArms(arms);
        customer.setUsername(username);
        customer.setPassword(password);
        customerRepository.save(customer);
//        Integer id=customer.getUserId;
//        customer = null;
//        customer = CustomerRepository.findCustomerByUserId(id);
//        assertNotNull(customer);
//        assertEquals(customer.getUserId(), id);
//        assertEquals(customer.getPhoneNo(), phoneNo);
//        assertEquals(customer.getHomeAddress(), address);

    }

}
