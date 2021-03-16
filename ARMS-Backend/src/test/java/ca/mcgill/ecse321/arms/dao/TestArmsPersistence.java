package ca.mcgill.ecse321.arms.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;


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

import java.util.*;

@SpringBootTest
public class TestArmsPersistence {

	//autowired here
	@Autowired
	private AppointmentRepository appointmentRepository;
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
	}


	/**
	  * @author Linwei Yuan
	  */
	@Test
	public void testPersistAndLoadCustomer() {
		String username="TestUserName";
		String password="TestPassword";
		Customer customer = new Customer();
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
	  * @author Zhiwei Li
	  */
	 @Test
	 public void testPersistAndLoadCar() {
	  String username="TestUserName";
	  String password="TestPassword";
	  Customer customer = new Customer();
	  customer.setUsername(username);
	  customer.setPassword(password);
	  customerRepository.save(customer);
	  String id = customer.getUsername();

	  String plateNo="T1";
	  String manufactor="A";
	  Car car = new Car();
	  car.setPlateNo(plateNo);
	  car.setManufacturer(manufactor);
	  car.setCustomer(customer);
	  carRepository.save(car);
	  car = null;
	  car = carRepository.findCarByPlateNo(plateNo);
	  assertEquals(car.getManufacturer(), manufactor);
	  assertEquals(car.getCustomer().getUsername(), customer.getUsername());
	 }
	 
	 @Test
	 public void testPersistAndLoadBill() {
	  String username="TestUserName";
	  String password="TestPassword";
	  Customer customer = new Customer();
	  customer.setUsername(username);
	  customer.setPassword(password);
	  customerRepository.save(customer);
	  String id = customer.getUsername();

	  int billnum = 1;
	  int price = 100;
	  Bill bill = new Bill();
	  boolean payornot = false;
	  bill.setAmount(price);
	  bill.setBillNo(billnum);
	  bill.setCustomer(customer);
	  bill.setIsPaid(payornot);  //test
	  billRepository.save(bill);

	  bill=null;
	  bill=billRepository.findBillByBillNo(billnum);
	  assertNotNull(bill);
	  assertEquals(bill.getAmount(), price);
	  assertEquals(bill.getCustomer().getUsername(), customer.getUsername());
	 }
	 
	 /**
	  * @author Jianmo Li
	  */
	 @Test
	 public void testPersistenceAndLoadAssistant() {
	  String username = "tester";
	  String password = "1234";
	  Assistant ass = new Assistant();
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
		 * @author Ruixin Su
		 */
		@Test
		public void testPersistenceAndLoadTechnician() {
			String sd = "2010-01-03";
			java.sql.Date startd = Date.valueOf(sd);
			String ed = "2020-02-11";
			java.sql.Date endd = Date.valueOf(ed);
			long st = 16277824;
			java.sql.Time startt = new Time(st);
			long et = 162478332;
			java.sql.Time endt = new Time(et);
			long timeslotid = 2347;

			TimeSlot timeslot = new TimeSlot();
			timeslot.setEndDate(endd);
			timeslot.setEndTime(endt);
			timeslot.setStartDate(startd);
			timeslot.setStartTime(startt);
			timeslot.setTimeslotID(timeslotid);
			timeSlotRepository.save(timeslot);

			Set<TimeSlot> ts = new HashSet<>();
			// ts.add(timeslot1);
			ts.add(timeslot);

			String tname = "technician1";
			int tid = 137;
			Technician tech = new Technician();
			tech.setName(tname);
			tech.setTechnicianID(tid);
			tech.setTimeSlot(ts);
			technicianRepository.save(tech);

			tech = null;
			tech = this.technicianRepository.findTechnicianByTechnicianID(tid);
			assertNotNull(tech);
			assertEquals(tech.getTechnicianID(), tid);
//	      assertEquals(tech.getTimeSlot().iterator().next(),timeslot);
		}
	
	/**
	  * @author Linwei Yuan
	  */	
	@Test
    public void testPersistenceAndLoadSpace() {
        String sd = "2010-01-03";
        Date startd = Date.valueOf(sd);
        String ed = "2020-02-11";
        Date endd = Date.valueOf(ed);
        long st = 16277824;
        Time startt = new Time(st);
        long et = 162478332;
        Time endt = new Time(et);
        long timeslotid = 2347;
        TimeSlot timeslot = new TimeSlot();
        timeslot.setEndDate(endd);
        timeslot.setEndTime(endt);
        timeslot.setStartDate(startd);
        timeslot.setStartTime(startt);
        timeslot.setTimeslotID(timeslotid);
        
        this.timeSlotRepository.save(timeslot);
        Set<TimeSlot> ts = new HashSet<>();
	ts.add(timeslot);
	int id = 1;
        Space space = new Space();
        space.setTimeSlot(ts);
        space.setSpaceID(id);
        spaceRepository.save(space);

        space = null;
        space = spaceRepository.findSpaceBySpaceID(id);
        assertNotNull(space);
        assertEquals(space.getSpaceID(),id);

    }


	/**
	 * @author Cecilia Jiang
	 */
//	@Test
//	public void testPersistenceAndLoadBusinessHour() {
//		String name = "TestBusinessName";
//		String address = "TestAddress, QC, CA";
//		String phoneNumber = "88888888";
//		String email = "test@mail.mcgill.ca";
//		Business business = new Business();
//
//		ARMS arms = new ARMS();
//		arms.setArmsID(2);
//		armsRepository.save(arms);
//
//		java.sql.Time startTime1 = java.sql.Time.valueOf("10:00:00");
//		java.sql.Time endTime1 = java.sql.Time.valueOf("17:00:00");
//		BusinessHour businessHour1 = new BusinessHour();
//		businessHour1.setStartTime(startTime1);
//		businessHour1.setEndTime(endTime1);
//		businessHour1.setBusinessHourID(1);
//		businessHour1.setARMS(arms);
//
//		businessHourRepository.save(businessHour1);
//
//		assertNotNull(businessHour1);
//		assertEquals(businessHour1.getStartTime().toString(), startTime1.toString());
//
//	}
	
	/**
	  * @author Cecilia Jiang
	  */
	 @Test
	 public void testPersistenceAndLoadService() {
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
		 * @author Ruixin Su
		 */
		@Test
		public void testPersistenceAndLoadAppointment() {

			TimeSlot timeslot = new TimeSlot();
			String name = "TestServiceName";
			int duration = 60;
			int price = 100;
			Service service = new Service();

			service.setName(name);
			service.setDuration(duration);
			service.setPrice(price);
			this.serviceRepository.save(service);

			String username = "TestUserName";
			String password = "TestPassword";
			Customer customer = new Customer();
			customer.setUsername(username);
			customer.setPassword(password);
			customerRepository.save(customer);
			String id = customer.getUsername();

			String plateNo = "T1";
			String manufactor = "A";
			Car car = new Car();
			car.setPlateNo(plateNo);
			car.setManufacturer(manufactor);
			car.setCustomer(customer);
			carRepository.save(car);

			int pn = 12345;

			String sd = "2020-01-01";
			Date startd = Date.valueOf(sd);
			String ed = "2020-02-02";
			Date endd = Date.valueOf(ed);
			long st = 1624782;
			Time startt = new Time(st);
			long et = 162478432;
			Time endt = new Time(et);
			long timeslotid = 2345;
			timeslot.setEndDate(endd);
			timeslot.setEndTime(endt);
			timeslot.setStartDate(startd);
			timeslot.setStartTime(startt);
			timeslot.setTimeslotID(timeslotid);
			this.timeSlotRepository.save(timeslot);

			Appointment app = new Appointment();
			int appid = 3456;
			app.setCar(car);
			app.setService(service);
			app.setTimeSlot(timeslot);
			app.setAppointmentID(appid);
			this.appointmentRepository.save(app);

			app = null;
			app = this.appointmentRepository.findAppointmentByAppointmentID(appid);
			assertNotNull(app);
			assertEquals(app.getAppointmentID(), appid);
//			assertEquals(app.getCar().getPlateNo(), pn);

		}
}
