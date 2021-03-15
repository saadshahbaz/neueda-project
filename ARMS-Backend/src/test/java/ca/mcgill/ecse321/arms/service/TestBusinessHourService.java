package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.dao.BusinessHourRepository;
import ca.mcgill.ecse321.arms.dao.BussinessRepository;
import ca.mcgill.ecse321.arms.model.Bill;
import ca.mcgill.ecse321.arms.model.Business;
import ca.mcgill.ecse321.arms.model.BusinessHour;
import ca.mcgill.ecse321.arms.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class TestBusinessHourService {
    @Mock
    private BussinessRepository businessDao;
    @Mock
    private BusinessHourRepository businessHourDao;

    @InjectMocks
    private BusinessService businessService;


    private static final String BUSINESS_KEY = "TestBusiness";
    private static final String NONEXISTING_BUSINESS_KEY = "NE_Business";

    private static final Long BUSINESSHOUR_KEY = 20020808080808L;
    private static final Long NE_USINESSHOUR_KEY = 20040808080808L;
    private static Business business;
    @BeforeEach
    public void setMockOutput() {
        lenient().when(businessDao.findBusinessByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(BUSINESS_KEY)) {
                Business business = new Business();
                business.setName(BUSINESS_KEY);
                businessDao.save(business);
                return business;
            } else {
                return null;
            }
        });
        lenient().when(businessHourDao.findBusinessHourByBusinessHourID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(BUSINESSHOUR_KEY)) {
                BusinessHour businessHour = new BusinessHour();
                businessHour.setBusinessHourID(BUSINESSHOUR_KEY);
                Business business = new Business();
                business.setName(BUSINESS_KEY);
                businessHour.setBusiness(business);
                businessDao.save(business);
                businessHourDao.save(businessHour);
                return businessHour;
            } else {
                return null;
            }
        });
        lenient()
                .when(businessHourDao.findAll())
                .thenAnswer(
                        (InvocationOnMock invocation) -> {
                            List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
                            businessHours.add(businessHourDao.findBusinessHourByBusinessHourID(BUSINESSHOUR_KEY));
                            return businessHours;
                        });

        lenient().when(businessHourDao.findBusinessHourByBusiness(any(Business.class)))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (((Business) invocation.getArgument(0)).getName().equals(BUSINESS_KEY)) {
                        ArrayList<BusinessHour> businessHours = new ArrayList<>();
                        BusinessHour businessHour = new BusinessHour();
                        businessHour.setBusiness(((Business) invocation.getArgument(0)));
                        businessHour.setBusinessHourID(BUSINESSHOUR_KEY);

                        businessHours.add(businessHour);
                        return businessHours;
                    } else {
                        return null;
                    }
                });
        lenient().when(businessHourDao.deleteBusinessHourByBusinessHourID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BUSINESSHOUR_KEY)) {
                return 1;
            } else {
                return 0;
            }
        });
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(businessDao.save(any(Business.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(businessHourDao.save(any(BusinessHour.class))).thenAnswer(returnParameterAsAnswer);

}
    @AfterEach
    public void clearDataBase(){
        businessHourDao.deleteAll();
    }
    @Test
    public void testCreateBusinessHour() {
        //assertEquals(0, service.getAllPersons().size());

        String startDate = "2002-03-02";
        String endDate = "2002-04-02";
        String startTime = "08:00:00";
        String endTime = "18:00:00";
        Business business = businessDao.findBusinessByName(BUSINESS_KEY);
        BusinessHour businessHour = null;

        try {
            businessHour = businessService.createBusinessHour(startDate,endDate,startTime,endTime,business);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(businessHour);
        assertEquals(20020302080000L, businessHour.getBusinessHourID());
    }

    @Test
    public void testCreateExistingBusinessHour() {
        //assertEquals(0, service.getAllPersons().size());
        String error = null;

        String startDate = "2002-08-08";
        String endDate = "2002-09-02";
        String startTime = "08:08:08";
        String endTime = "18:00:00";
        Business business = businessDao.findBusinessByName(BUSINESS_KEY);
        BusinessHour businessHour = null;

        try {
            businessHour = businessService.createBusinessHour(startDate,endDate,startTime,endTime,business);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals("BusinessHour id already exists", error);
    }

    @Test
    public void testCreateBusinessHourStartAfterEnd() {
        //assertEquals(0, service.getAllPersons().size());
        String error = null;

        String startDate = "2002-07-07";
        String endDate = "2002-06-02";
        String startTime = "08:00:00";
        String endTime = "18:00:00";
        Business business = businessDao.findBusinessByName(BUSINESS_KEY);
        BusinessHour businessHour = null;

        try {
            businessHour = businessService.createBusinessHour(startDate,endDate,startTime,endTime,business);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals("Start date can not be after end date.", error);
    }

    @Test
    public void testCreateOverlappingBusinessHour() {
        //assertEquals(0, service.getAllPersons().size());
        String error = null;

        String startDate = "2002-07-07";
        String endDate = "2002-08-02";
        String startTime = "08:00:00";
        String endTime = "18:00:00";
        String startDate1 = "2002-07-07";
        String endDate1 = "2002-07-22";
        String startTime1 = "08:00:00";
        String endTime1 = "18:00:00";
        Business business = businessDao.findBusinessByName(BUSINESS_KEY);
        BusinessHour businessHour = null;
        BusinessHour businessHour1 = null;
        businessHour = businessService.createBusinessHour(startDate,endDate,startTime,endTime,business);
        System.out.println(business.getBusinessHour());

        try {
            businessHour1 = businessService.createBusinessHour(startDate1,endDate1,startTime1,endTime1,business);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            error = e.getMessage();
        }
        assertNull(businessHour1);
        assertEquals("BusinessHour time period cannot overlap with existing businessHours.", error);
    }

    @Test
    public void testGetBusinessHourById() {
        assertEquals(BUSINESSHOUR_KEY, businessService.getBusinessHour(BUSINESSHOUR_KEY).getBusinessHourID());
    }

    @Test
    public void testGetNonExistingBusinessHourById() {
        BusinessHour businessHour = null;
        String error = null;
        try {
            businessHour = businessService.getBusinessHour(NE_USINESSHOUR_KEY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("BusinessHour doesn't exist", error);
    }

    @Test
    public void testDeleteExistingBusinessHour() {
        String error = null;
        Integer i = null;

        try {
            i = businessService.deleteBusinessHour(BUSINESSHOUR_KEY);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertEquals(1,i);
    }

    @Test
    public void testGetAllBusinessHour() {
        assertEquals(BUSINESSHOUR_KEY, businessService.getAllBusinessHour().get(0).getBusinessHourID());
    }

    @Test
    public void testGetBusinessHourByExistingBusiness() {
        List<BusinessHour> businessHours=new ArrayList<>();
        String username=BUSINESS_KEY;
        business = businessDao.findBusinessByName(BUSINESS_KEY);

        String error = null;

        BusinessHour businessHour = null;
        try {
            businessHours = businessService.getBusinessHourByBusiness(BUSINESS_KEY);


            businessHour=businessHours.get(0);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertEquals(BUSINESSHOUR_KEY,businessHour.getBusinessHourID());

    }


    @Test
    public void testGetBusinessHourByNonExistingBusiness() {
        List<BusinessHour> businessHours=new ArrayList<>();


        String error = null;
        BusinessHour businessHour = null;
        try {
            business = businessDao.findBusinessByName(NONEXISTING_BUSINESS_KEY);
            businessHours = businessService.getBusinessHourByBusiness(NONEXISTING_BUSINESS_KEY);
            businessHour=businessHours.get(0);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        assertEquals("Business doesn't exist.",error);

    }

}


