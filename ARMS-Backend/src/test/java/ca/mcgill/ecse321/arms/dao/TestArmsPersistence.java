package ca.mcgill.ecse321.arms.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.hibernate.mapping.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.arms.dao.*;
import ca.mcgill.ecse321.arms.model.*;

import java.util.HashSet;
import java.util.Set;

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
	@Autowired
	private BussinessRepository businessRepository;

	@BeforeEach
	@AfterEach
	public void clearDB() {
		appointmentRepository.deleteAll();
		assistantRepository.deleteAll();
		billRepository.deleteAll();
		businessHourRepository.deleteAll();
		businessRepository.deleteAll();
		carRepository.deleteAll();
		customerRepository.deleteAll();
		serviceRepository.deleteAll();
		spaceRepository.deleteAll();
		technicianRepository.deleteAll();
		timeSlotRepository.deleteAll();
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
	
//	@Test
//    public void testPersistenceAndLoadSpace() {
//
//        ARMS arms = new ARMS();
//        arms.setArmsID(3);
//        armsRepository.save(arms);
//
//        Space space = new Space();
//        space.setARMS(arms);
//        spaceRepository.save(space);
//
//        String sd = "2010-01-03";
//        Date startd = Date.valueOf(sd);
//        String ed = "2020-02-11";
//        Date endd = Date.valueOf(ed);
//        long st = 16277824;
//        Time startt = new Time(st);
//        long et = 162478332;
//        Time endt = new Time(et);
//        int timeslotid = 2347;
//        TimeSlot timeslot = new TimeSlot();
//        timeslot.setEndDate(endd);
//        timeslot.setEndTime(endt);
//        timeslot.setStartDate(startd);
//        timeslot.setStartTime(startt);
//        timeslot.setTimeslotID(timeslotid);
//        timeslot.setARMS(arms);
//        space.setTimeSlot((Set<TimeSlot>) timeslot);
//        this.timeSlotRepository.save(timeslot);
//
//        Integer id = space.getSpaceID();
//        space = null;
//        space = spaceRepository.findSpaceBySpaceId(id);
//        assertNotNull(space);
//        assertEquals(space.getSpaceID(),id);
//
//    }


	/**
	 * @author Cecilia Jiang
	 */
	@Test
	public void testPersistenceAndLoadBusinessHour() {
		String name = "TestBusinessName";
		String address = "TestAddress, QC, CA";
		String phoneNumber = "88888888";
		String email = "test@mail.mcgill.ca";
		Business business = new Business();

		ARMS arms = new ARMS();
		arms.setArmsID(2);
		armsRepository.save(arms);
		business.setARMS(arms);
		business.setName(name);
		business.setAddress(address);
		business.setPhoneNumber(phoneNumber);
		business.setEmail(email);
		businessRepository.save(business);

		java.sql.Time startTime1 = java.sql.Time.valueOf("10:00:00");
		java.sql.Time endTime1 = java.sql.Time.valueOf("17:00:00");
		BusinessHour businessHour1 = new BusinessHour();
		businessHour1.setStarttime(startTime1);
		businessHour1.setEndTime(endTime1);
		businessHour1.setBusinessHourID(1);

		//Set<BusinessHour> businessHour = new HashSet<BusinessHour>();
		//businessHour.add(businessHour1);
		//business.setBusinessHour(businessHour);
		businessHourRepository.save(businessHour1);

		

		businessHour1 = null;
		businessHour1 = this.businessHourRepository.findBusinessHourByBusinessHourID(1);
		assertNotNull(businessHour1);
		assertEquals(businessHour1.getStarttime().toString(), startTime1.toString());

	}
}