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
    private static int spaceID = 0;

    private static String NAME = "TestTechnician";
    private static int technicianID = 0;
    private static String EMAIL = "technician@mail.ca";


    private static final String BUSINESS_KEY = "TestBusiness";
    private static final String NONEXISTING_BUSINESS_KEY = "NE_Business";

    private static final Long BUSINESSHOUR_KEY = 20020808080808L;
    private static final Long NE_USINESSHOUR_KEY = 20040808080808L;
    private static final Long TimeSlot_KEY = 2004050710205500L;
    private static Business business;
    @BeforeEach
    public void setMockOutput() {
        lenient().when(timeSlotRepository.findTimeSlotByTimeSlotID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TimeSlot_KEY)) {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeslotID(TimeSlot_KEY);
                Business business = new Business();
                Space space = new Space();
                space.setSpaceID(spaceID);
                Technician technician = new Technician();
                technician.setTechnicianID(technicianID);
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
                    if (((Space) invocation.getArgument(0)).getSpaceID()==spaceID) {
                        List<TimeSlot> timeSlots = new ArrayList<>();
                        TimeSlot timeSlot = new TimeSlot();
                        timeSlot.setSpace(((Space) invocation.getArgument(0)));
                        timeSlot.setTimeslotID(TimeSlot_KEY);

                        Date startDate = Date.valueOf("2004-05-07");
                        Time startTime = Time.valueOf("10:20:55");
                        Date endDate = Date.valueOf("2004-05-07");
                        Time endTime = Time.valueOf("13:34:32");
                        timeSlot.setStartDate(startDate);
                        timeSlot.setStartTime(startTime);
                        timeSlot.setEndDate(endDate);
                        timeSlot.setEndTime(endTime);
                        Technician technician = technicianRepository.findTechnicianByTechnicianID(technicianID);
                        timeSlot.setTechnician(technician);

                        timeSlots.add(timeSlot);
                        return timeSlots;
                    } else {
                        return null;
                    }
                });

        lenient().when(timeSlotRepository.findTimeSlotsByTechnician(any(Technician.class)))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (((Technician) invocation.getArgument(0)).getTechnicianID()==technicianID) {
                        List<TimeSlot> timeSlots = new ArrayList<>();
                        TimeSlot timeSlot = new TimeSlot();
                        timeSlot.setTechnician(((Technician) invocation.getArgument(0)));
                        timeSlot.setTimeslotID(TimeSlot_KEY);
                        Date startDate = Date.valueOf("2004-05-07");
                        Time startTime = Time.valueOf("10:20:55");
                        Date endDate = Date.valueOf("2004-05-07");
                        Time endTime = Time.valueOf("13:34:32");
                        timeSlot.setStartDate(startDate);
                        timeSlot.setStartTime(startTime);
                        timeSlot.setEndDate(endDate);
                        timeSlot.setEndTime(endTime);
                        Space space = spaceRepository.findSpaceBySpaceID(spaceID);
                        timeSlot.setSpace(space);


                        timeSlots.add(timeSlot);
                        return timeSlots;
                    } else {
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
            if (invocation.getArgument(0).equals(spaceID)) {
                Space space = new Space();
                space.setSpaceID(spaceID);
                return space;
            } else {
                return null;
            }
        });
        lenient().when(technicianRepository.findTechnicianByTechnicianID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(technicianID)) {
                Technician technician = new Technician();
                technician.setTechnicianID(technicianID);
                technician.setEmail(EMAIL);
                technician.setName(NAME);
                return technician;
            } else {
                return null;
            }
        });
        lenient().when(timeSlotRepository.deleteTimeSlotByTimeslotID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TimeSlot_KEY)) {
                return 1;
            } else {
                return 0;
            }
        });
        lenient().when(timeSlotRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
            timeSlots.add(timeSlotRepository.findTimeSlotByTimeSlotID(TimeSlot_KEY));
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
            timeSlot = timeSlotService.createTimeSlot(BUSINESS_KEY,startDate,startTime,endDate,endTime,spaceID,technicianID);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(timeSlot);
        assertEquals(2002090609000000L, timeSlot.getTimeslotID());
    }


}
