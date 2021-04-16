package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.dao.BusinessHourRepository;
import ca.mcgill.ecse321.arms.dao.BussinessRepository;
import ca.mcgill.ecse321.arms.model.Business;
import ca.mcgill.ecse321.arms.model.BusinessHour;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
public class TestBusinessService {

    @Mock
    private BussinessRepository businessDao;
    @Mock
    private BusinessHourRepository businessHourDao;

    @InjectMocks
    private BusinessService businessService;

    private static final String BUSINESS_KEY = "TestBusiness";
    private static final String NONEXISTING_BUSINESS_KEY = "NE_Business";

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
        lenient()
                .when(businessDao.findAll())
                .thenAnswer(
                        (InvocationOnMock invocation) -> {
                            List<Business> Businesses = new ArrayList<Business>();
                            Businesses.add(businessDao.findBusinessByName(BUSINESS_KEY));
                            return Businesses;
                        });
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(businessDao.save(any(Business.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(businessHourDao.save(any(BusinessHour.class))).thenAnswer(returnParameterAsAnswer);


    }

    @Test
    public void testCreateBusiness() {
        //assertEquals(0, service.getAllPersons().size());

        String name = "business1";
        String address = "ave.abc 1234";
        String phoneNumber = "1234";
        String email = "1@234";
        Business business = null;
        try {
            business = businessService.createBusiness(name,address,phoneNumber,email);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(business);
        assertEquals(name, business.getName());
    }

    @Test
    public void testCreateBusinessNull() {
        String name = null;
        String error = null;
        String address = "ave.abc 1234";
        String phoneNumber = "1234";
        String email = "1@234";
        Business business = null;
        try {
            business = businessService.createBusiness(name,address,phoneNumber,email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        // check error
        assertEquals("Business Name cannot be empty.", error);
    }

    @Test
    public void testCreateBusinessEmpty() {
        String name = "";
        String error = null;
        String address = "ave.abc 1234";
        String phoneNumber = "1234";
        String email = "1@234";
        Business business = null;
        try {
            business = businessService.createBusiness(name,address,phoneNumber,email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        // check error
        assertEquals("Business Name cannot be empty.", error);
    }

    @Test
    public void testCreateBusinessSpaces() {
        String name = " ";
        String error = null;
        String address = "ave.abc 1234";
        String phoneNumber = "1234";
        String email = "1@234";
        Business business = null;
        try {
            business = businessService.createBusiness(name,address,phoneNumber,email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(business);
        // check error
        assertEquals("Business Name cannot be empty.", error);
    }

    @Test
    public void testGetExistingBusiness() {
        assertEquals(BUSINESS_KEY, businessService.getBusiness(BUSINESS_KEY).getName());
    }

    @Test
    public void testGetNonExistingBusiness() {
        Business business = null;
        String error = null;
        try {
            business = businessService.getBusiness(NONEXISTING_BUSINESS_KEY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("Business doesn't exist.", error);
    }

    @Test
    public void testGetAllBusiness() {
        assertEquals(BUSINESS_KEY, businessService.getAllBusiness().get(0).getName());
    }

    private int transfer(Date startDate, Time startTime){
        String date = startDate.toString();
        String time = startTime.toString();
        String res = date+time;
        res = res.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "");
        return Integer.parseInt(res);
    }





}
