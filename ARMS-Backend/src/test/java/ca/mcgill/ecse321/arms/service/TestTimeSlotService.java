package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.dao.*;
import ca.mcgill.ecse321.arms.model.*;
import org.junit.After;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestTimeSlotService {
    @Mock
    private BussinessRepository businessDao;
    @Mock
    private SpaceRepository spaceRepository;
    @Mock
    private TechnicianRepository technicianRepository;
    @Mock
    private BusinessHourRepository businessHourDao;
    @Mock
    private TimeSlotRepository timeSlotRepository;

    @InjectMocks
    private TimeSlotService timeSlotService;
    private BusinessService businessService;
    private SpaceService spaceService;
    private static int spaceID1 = 1;
    private static int spaceID2 = 2;

    private static String NAME = "TestTechnician";
    private static int technicianID1 = 1;
    private static int technicianID2 = 2;
    private static String EMAIL = "technician@mail.ca";


    private static final String BUSINESS_KEY = "TestBusiness";
    private static final String NONEXISTING_BUSINESS_KEY = "NE_Business";

    private static final Long BUSINESSHOUR_KEY = 20020808080808L;
    private static final Long NE_USINESSHOUR_KEY = 20040808080808L;
    private static final Long TimeSlot_KEY1 = 2004050710205511L;
    private static final Long TimeSlot_KEY2 = 2004050710205522L;
    private static Business business;
    @BeforeEach
    public void setMockOutput() {
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
                technician.setEmail(EMAIL);
                technician.setName(NAME);
                return technician;
            } else {
                return null;
            }
        });
        lenient().when(timeSlotRepository.deleteTimeSlotByTimeslotID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TimeSlot_KEY1)) {
                return 1;
            } else {
                return 0;
            }
        });
        lenient().when(timeSlotRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
            timeSlots.add(timeSlotRepository.findTimeSlotByTimeslotID(TimeSlot_KEY1));
            return timeSlots;
        });

    }

    @AfterEach
    public void clearDataBase(){
        timeSlotRepository.deleteAll();
    }
    @Test
    public void testCreateTimeSlot() {
        //assertEquals(0, service.getAllPersons().size());

        String startDate = "2002-09-06";
        String endDate = "2002-09-06";
        String startTime = "09:00:00";
        String endTime = "18:00:00";
        TimeSlot timeSlot = null;

        try {
            timeSlot = timeSlotService.createTimeSlot(BUSINESS_KEY,startDate,startTime,endDate,endTime,spaceID1,technicianID1);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(timeSlot);
        assertEquals(2002090609000011L, timeSlot.getTimeslotID());
    }

    @Test
    public void testCreateTimeSlotConflictWithSpace() {
        //assertEquals(0, service.getAllPersons().size());
        String error = null;


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
        TimeSlot timeSlot1 = null;

        TimeSlot timeSlot2 = null;


        try {
            timeSlot2 = timeSlotService.createTimeSlot(BUSINESS_KEY,startDate1,startTime1,endDate1,endTime1,1,2);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            error = e.getMessage();
        }
        assertNull(timeSlot2);
        assertEquals("cannot build such timeSlot since no free space!", error);
    }

    @Test
    public void testCreateTimeSlotConflictWithTech() {
        //assertEquals(0, service.getAllPersons().size());
        String error = null;


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
        TimeSlot timeSlot1 = null;

        TimeSlot timeSlot2 = null;


        try {
            timeSlot2 = timeSlotService.createTimeSlot(BUSINESS_KEY,startDate1,startTime1,endDate1,endTime1,2,1);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            error = e.getMessage();
        }
        assertNull(timeSlot2);
        assertEquals("cannot build such timeSlot since no free tech !", error);
    }

    @Test
    public void testCreateTimeSlotConflictBusinessHour() {
        //assertEquals(0, service.getAllPersons().size());
        String error = null;


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
        TimeSlot timeSlot1 = null;

        TimeSlot timeSlot2 = null;


        try {
            timeSlot2 = timeSlotService.createTimeSlot(BUSINESS_KEY,startDate1,startTime1,endDate1,endTime1,2,1);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            error = e.getMessage();
        }
        assertNull(timeSlot2);
        assertEquals("cannot build such timeSlot since no free businessHour!", error);
    }

    @Test
    public void testGetAllTimeSlot() {
        assertEquals(TimeSlot_KEY1, timeSlotService.getAllTimeSlots().get(0).getTimeslotID());
    }

    @Test
    public void testDeleteExistingTimeSlot() {
        String error = null;
        Integer i = null;

        try {
            i = timeSlotService.deleteTimeSlot(TimeSlot_KEY1);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertEquals(1,i);
    }



}
