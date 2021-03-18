package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.ArmsApplication;
import ca.mcgill.ecse321.arms.dao.*;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestAppointmentService {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @Mock
    private SpaceRepository spaceRepository;

    @Mock
    private TechnicianRepository technicianRepository;

    @Mock
    private BusinessHourRepository businessHourDao;

    @Mock
    private BussinessRepository businessDao;

    @InjectMocks
    private AppointmentService appointmentService;

    @InjectMocks
    private TimeSlotService timeSlotService;

    //create service parameter
    private static final String SERVICENAME = "TestService";
    private static final int DURATION = 30;
    private static final int PRICE = 100;
    //create car parameter
    private static final String MANUFACTURER="abc";
    private static final String MODEL = "AB";
    private static final String YEAR="1948";
    private static final String PLATENO = "A123B4";

    private static final String TEST_Username1 = "TestUsername1";
    private static final String TEST_Password1 = "Testpassword1";
    private static final String Test_Email1 ="6722024081@qq.com";
    private static final String Test_phone1 ="123456781";

    private static final String Test_manufacturer1="abcd";
    private static final String Test_model1 = "ABC";
    private static final String Test_year1="1941";
    private static final String Test_plateN1 = "A123B9";
    //create time slot parameter
    private static final String STARTDATE = "2021-03-01";
    private static final String STARTTIME = "9:00";
    private static final String ENDDATE = "2021-03-01";
    private static final String ENDTIME = "10:00";

    private static final String BUSINESS_KEY = "TestBusiness";
    private static final String NONEXISTING_BUSINESS_KEY = "NE_Business";
    private static final Long BUSINESSHOUR_KEY = 20020808080808L;
    private static final Long NE_USINESSHOUR_KEY = 20040808080808L;
    private static final Long TimeSlot_KEY1 = 2004050710205511L;
    private static final Long TimeSlot_KEY2 = 2004050710205522L;
    //create technician parameter
    private static final int TECHID = 3;
    private static final String TECHNAME = "testName";
    private static final String TECHEMAIL = "testTechnician@mail.ca";
    private static final int technicianID1 = 1;
    private static final int technicianID2 = 2;
    //create space parameter
    private static final int SPACEID = 3;
    private static final int spaceID1 = 1;
    private static final int spaceID2 = 2;
    //create customer parameter
    private static final String LASTREMINDER = "2021-03-01";
    private static final String USERNAME = "testName";
    private static final String PASSWORD = "testPassword";
    private static final String EMAIL = "test@mail.ca";
    private static final String PHONENUMBER = "88888888";

    private static final int APPOINTMENTID = 1;

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
//                        Appointment appointment=new Appointment();
//                        appointment.setAppointmentID(0);
//                        appointment.setCar(((Car) invocation.getArgument(0)));
//                        return appointment;
                        Service service = createService(SERVICENAME,DURATION,PRICE);
                        Customer customer = createCustomer(LASTREMINDER, USERNAME, PASSWORD, EMAIL, PHONENUMBER);
                        Car car = createCar(customer, MODEL, MANUFACTURER, PLATENO, YEAR);
                        Technician technician = createTechnician(TECHID, TECHNAME, TECHEMAIL);
                        Space space = createSpace(SPACEID);
                        TimeSlot timeSlot = createTimeSlot(technician, space, STARTDATE, STARTTIME, ENDDATE, ENDTIME);
                        Appointment appointment = createAppointment(APPOINTMENTID, car, service, timeSlot);
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

        // mock for findAll
        lenient().when(appointmentRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
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

        // mock for findCarByplateNo
        lenient().when(carRepository.findCarByPlateNo(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(PLATENO)) {
                Customer customer = new Customer();
                customer.setPassword(PASSWORD);
                customer.setUsername(USERNAME);
                customer.setEmail(EMAIL);
                customer.setPhoneNumber(PHONENUMBER);
                Car car = new Car();
                car.setModel(MODEL);
                car.setYear(YEAR);
                car.setManufacturer(MANUFACTURER);
                car.setPlateNo(PLATENO);
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

        lenient().when(timeSlotRepository.findTimeSlotByTimeslotID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TimeSlot_KEY1)) {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeslotID(TimeSlot_KEY1);
                Business business = new Business();
                Space space = new Space();
                space.setSpaceID(spaceID1);
                Technician technician = new Technician();
                technician.setTechnicianID(technicianID1);
                spaceRepository.save(space);
                technicianRepository.save(technician);
                timeSlotRepository.save(timeSlot);
                return timeSlot;
            } else {
                return null;
            }
        });

        lenient().when(timeSlotRepository.findTimeSlotsBySpace(any(Space.class)))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (((Space) invocation.getArgument(0)).getSpaceID()==spaceID1) {
                        List<TimeSlot> timeSlots = new ArrayList<>();
                        TimeSlot timeSlot = new TimeSlot();
                        timeSlot.setSpace(((Space) invocation.getArgument(0)));
                        timeSlot.setTimeslotID(TimeSlot_KEY1);

                        Date startDate = Date.valueOf("2004-05-07");
                        Time startTime = Time.valueOf("10:20:55");
                        Date endDate = Date.valueOf("2004-05-07");
                        Time endTime = Time.valueOf("13:34:32");
                        timeSlot.setStartDate(startDate);
                        timeSlot.setStartTime(startTime);
                        timeSlot.setEndDate(endDate);
                        timeSlot.setEndTime(endTime);
                        Technician technician = technicianRepository.findTechnicianByTechnicianID(technicianID1);
                        timeSlot.setTechnician(technician);

                        timeSlots.add(timeSlot);
                        return timeSlots;
                    }
                    else if (((Space) invocation.getArgument(0)).getSpaceID()==spaceID2) {
                        List<TimeSlot> timeSlots = new ArrayList<>();
                        TimeSlot timeSlot = new TimeSlot();
                        timeSlot.setSpace(((Space) invocation.getArgument(0)));
                        timeSlot.setTimeslotID(TimeSlot_KEY2);

                        Date startDate = Date.valueOf("2004-05-07");
                        Time startTime = Time.valueOf("10:20:55");
                        Date endDate = Date.valueOf("2004-05-07");
                        Time endTime = Time.valueOf("13:34:32");
                        timeSlot.setStartDate(startDate);
                        timeSlot.setStartTime(startTime);
                        timeSlot.setEndDate(endDate);
                        timeSlot.setEndTime(endTime);
                        Technician technician = technicianRepository.findTechnicianByTechnicianID(technicianID2);
                        timeSlot.setTechnician(technician);

                        timeSlots.add(timeSlot);
                        return timeSlots;
                    }else {
                        return null;
                    }
                });

        lenient().when(timeSlotRepository.findTimeSlotsByTechnician(any(Technician.class)))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (((Technician) invocation.getArgument(0)).getTechnicianID()==technicianID1) {
                        List<TimeSlot> timeSlots = new ArrayList<>();
                        TimeSlot timeSlot = new TimeSlot();
                        timeSlot.setTechnician(((Technician) invocation.getArgument(0)));
                        timeSlot.setTimeslotID(TimeSlot_KEY1);
                        Date startDate = Date.valueOf("2004-05-07");
                        Time startTime = Time.valueOf("10:20:55");
                        Date endDate = Date.valueOf("2004-05-07");
                        Time endTime = Time.valueOf("13:34:32");
                        timeSlot.setStartDate(startDate);
                        timeSlot.setStartTime(startTime);
                        timeSlot.setEndDate(endDate);
                        timeSlot.setEndTime(endTime);
                        Space space = spaceRepository.findSpaceBySpaceID(spaceID1);
                        timeSlot.setSpace(space);


                        timeSlots.add(timeSlot);
                        return timeSlots;
                    }
                    else if (((Technician) invocation.getArgument(0)).getTechnicianID()==technicianID2) {
                        List<TimeSlot> timeSlots = new ArrayList<>();
                        TimeSlot timeSlot = new TimeSlot();
                        timeSlot.setTechnician(((Technician) invocation.getArgument(0)));
                        timeSlot.setTimeslotID(TimeSlot_KEY2);
                        Date startDate = Date.valueOf("2004-05-07");
                        Time startTime = Time.valueOf("10:20:55");
                        Date endDate = Date.valueOf("2004-05-07");
                        Time endTime = Time.valueOf("13:34:32");
                        timeSlot.setStartDate(startDate);
                        timeSlot.setStartTime(startTime);
                        timeSlot.setEndDate(endDate);
                        timeSlot.setEndTime(endTime);
                        Space space = spaceRepository.findSpaceBySpaceID(spaceID2);
                        timeSlot.setSpace(space);


                        timeSlots.add(timeSlot);
                        return timeSlots;
                    }else {
                        return null;
                    }
                });



        lenient().when(businessDao.findBusinessByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BUSINESS_KEY)) {
                Business business = new Business();
                business.setName(BUSINESS_KEY);
                businessDao.save(business);
                return business;
            } else {
                return null;
            }
        });
        lenient().when(businessHourDao.findBusinessHourByBusinessHourID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BUSINESSHOUR_KEY)) {
                BusinessHour businessHour = new BusinessHour();
                businessHour.setBusinessHourID(BUSINESSHOUR_KEY);
                Business business = new Business();
                business.setName(NONEXISTING_BUSINESS_KEY);
                businessHour.setBusiness(business);
                businessDao.save(business);
                businessHourDao.save(businessHour);

                return businessHour;
            } else {
                return null;
            }
        });
        lenient().when(businessHourDao.findBusinessHourByBusiness(any(Business.class)))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (((Business) invocation.getArgument(0)).getName().equals(BUSINESS_KEY)) {
                        ArrayList<BusinessHour> businessHours = new ArrayList<>();
                        BusinessHour businessHour = new BusinessHour();
                        businessHour.setBusiness(((Business) invocation.getArgument(0)));
                        businessHour.setBusinessHourID(BUSINESSHOUR_KEY);
                        Date startDate = Date.valueOf("2002-08-08");
                        Time startTime = Time.valueOf("08:08:08");
                        Date endDate = Date.valueOf("2020-10-23");
                        Time endTime = Time.valueOf("23:34:32");
                        businessHour.setStartDate(startDate);
                        businessHour.setStartTime(startTime);
                        businessHour.setEndDate(endDate);
                        businessHour.setEndTime(endTime);
                        businessHours.add(businessHour);
                        return businessHours;
                    } else {
                        return null;
                    }
                });
        lenient().when(spaceRepository.findSpaceBySpaceID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(spaceID1)) {
                Space space = new Space();
                space.setSpaceID(spaceID1);
                return space;
            } else {
                return null;
            }
        });
        lenient().when(technicianRepository.findTechnicianByTechnicianID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(technicianID1)) {
                Technician technician = new Technician();
                technician.setTechnicianID(technicianID1);
                technician.setEmail(TECHEMAIL);
                technician.setName(TECHNAME);
                return technician;
            } else {
                return null;
            }
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
        //service param
        String serviceName = SERVICENAME;
        //car param
        String plateNo = PLATENO;
        //time slot param
        String startDate = "2019-03-02";
        String endDate = "2019-03-02";
        String startTime = "09:00:00";
        String endTime = "13:00:00";
        //create technician parameter
        int technicianID = TECHID;
        //create space parameter
        int spaceID = SPACEID;

        // initialize account to null, so we can see if appointment creation was successful
        Appointment appointment = null;


        try{
            appointment = appointmentService.createAppointment(serviceName,plateNo,BUSINESS_KEY,startDate,startTime,endDate,endTime,spaceID,technicianID);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(appointment);
        assertEquals(serviceName, appointment.getService().getName());
        assertEquals(plateNo, appointment.getCar().getPlateNo());
        assertEquals(2019030209000033L,appointment.getTimeSlot().getTimeslotID());

    }

    @Test
    public void testCreateAppointmentConflictWithSpace() {
        //assertEquals(0, service.getAllPersons().size());
        String error = null;

        //service param
        String serviceName = SERVICENAME;
        //car param
        String plateNo = PLATENO;
        //time slot param
        String startDate1 = "2004-05-07";
        String endDate1 = "2004-05-07";
        String startTime1 = "10:00:00";
        String endTime1 = "14:00:00";

        Space space1 = new Space();
        space1.setSpaceID(1);
        Technician technician1 = new Technician();
        technician1.setTechnicianID(1);
        Technician technician2 = new Technician();
        technician2.setTechnicianID(2);

        Appointment appointment = null;

        try {
            appointment = appointmentService.createAppointment(serviceName,plateNo,BUSINESS_KEY,startDate1,startTime1,endDate1,endTime1,1,2);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            error = e.getMessage();
        }
        assertNull(appointment);
        assertEquals("cannot build such appointment since no free space!", error);
    }

    @Test
    public void testCreateAppointmentConflictWithTech() {
        String error = null;
        //service param
        String serviceName = SERVICENAME;
        //car param
        String plateNo = PLATENO;
        //time slot param
        String startDate1 = "2004-05-07";
        String endDate1 = "2004-05-07";
        String startTime1 = "10:00:00";
        String endTime1 = "14:00:00";

        Space space1 = new Space();
        space1.setSpaceID(1);
        Space space2 = new Space();
        space2.setSpaceID(2);
        Technician technician1 = new Technician();
        technician1.setTechnicianID(1);
        Technician technician2 = new Technician();
        technician2.setTechnicianID(2);

        Appointment appointment = null;

        try {
            appointment = appointmentService.createAppointment(serviceName,plateNo,BUSINESS_KEY,startDate1,startTime1,endDate1,endTime1,2,1);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            error = e.getMessage();
        }
        assertNull(appointment);
        assertEquals("cannot build such appointment since no free tech !", error);
    }

    @Test
    public void testCreateAppointmentConflictBusinessHour() {

        String error = null;
        //service param
        String serviceName = SERVICENAME;
        //car param
        String plateNo = PLATENO;
        //time slot param
        String startDate1 = "1004-05-07";
        String endDate1 = "1004-05-07";
        String startTime1 = "10:00:00";
        String endTime1 = "14:00:00";
        Space space1 = new Space();
        space1.setSpaceID(1);
        Space space2 = new Space();
        space2.setSpaceID(2);
        Technician technician1 = new Technician();
        technician1.setTechnicianID(1);
        Technician technician2 = new Technician();
        technician2.setTechnicianID(2);

        Appointment appointment = null;

        try {
            appointment = appointmentService.createAppointment(serviceName,plateNo,BUSINESS_KEY,startDate1,startTime1,endDate1,endTime1,2,1);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            error = e.getMessage();
        }
        assertNull(appointment);
        assertEquals("cannot build such appointment since no free businessHour!", error);
    }


    @Test
    public void test_get_existing_appointment(){
        Appointment appointment = appointmentService.getAppointment(APPOINTMENTID);
        assertEquals(APPOINTMENTID, appointment.getAppointmentID());
    }

    @Test
    public void test_get_non_existing_appointment(){
        String error = "";
        try{
            assertNull(appointmentService.getAppointment(2));
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "Appointment with ID 2 does not exist");
    }

    @Test
    public void test_get_all_services(){
        List<Appointment> appointments = appointmentService.getAllAppointments();
        assertEquals(appointments.get(0).getAppointmentID(), APPOINTMENTID);
    }

    @Test
    public void testDeleteExistingAppointment() {
        ArmsApplication.setSystemDateAndTime();
        int i = 0;
        try {
            i = appointmentService.deleteAppointment(APPOINTMENTID);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            fail();
        }

        // check if not null and values are as expected
        assertEquals(1,i);
    }

    @Test
    public void test_delete_non_existing_appointment(){
        String error = "";
        try{
            assertNull(appointmentService.deleteAppointment(2));
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "No appointment was found.");
    }

    @Test
    public void test_delete_an_appointment_within_current_date(){
        ArmsApplication.setCurrentDate(stringToDate("2021-03-01"));
        String error = "";

        try{
            appointmentService.deleteAppointment(APPOINTMENTID);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertEquals(error, "You can not delete an appointment happening on the current date");
    }

    @Test
    public void test_update_an_appointment_successfully(){
        ArmsApplication.setSystemDateAndTime();
        //service param
        String serviceName = SERVICENAME;
        //car param
        String plateNo = PLATENO;
        //time slot param
        String startDate = "2019-03-02";
        String endDate = "2019-03-02";
        String startTime = "09:00:00";
        String endTime = "13:00:00";
        //create technician parameter
        int technicianID = TECHID;
        //create space parameter
        int spaceID = SPACEID;

        Appointment appointment = null;
        try {
            appointment = appointmentService.updateAppointment(APPOINTMENTID,serviceName,plateNo,BUSINESS_KEY,startDate,startTime,endDate,endTime,technicianID,spaceID);
        }catch(IllegalArgumentException e){
            fail();
        }

        assertNotNull(appointment);
        assertEquals(serviceName, appointment.getService().getName());
        assertEquals(plateNo, appointment.getCar().getPlateNo());
        assertEquals(2019030209000033L,appointment.getTimeSlot().getTimeslotID());
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