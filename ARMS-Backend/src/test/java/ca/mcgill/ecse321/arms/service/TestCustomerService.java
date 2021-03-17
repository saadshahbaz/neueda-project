package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.ArmsApplication;
import ca.mcgill.ecse321.arms.dao.*;
import ca.mcgill.ecse321.arms.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private BillRepository billRepository;
    @InjectMocks
    private CustomerService customerService;


    private static final String TEST_Username = "TestUsername";
    private static final String TEST_Password = "Testpassword";
    private static final String Test_Email ="672202408@qq.com";
    private static final String Test_phone ="12345678";

    private static final String TEST_Username1 = "TestUsername1";
    private static final String TEST_Password1 = "Testpassword1";
    private static final String Test_Email1 ="6722024081@qq.com";
    private static final String Test_phone1 ="123456781";

    @BeforeEach
    public void setMockOutput() {

        // mock for findCustomerByUsername
        lenient().when(customerRepository.findCustomerByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TEST_Username)) {
                Customer customer = new Customer();
                customer.setPassword(TEST_Password);
                customer.setUsername(TEST_Username);
                customer.setEmail(Test_Email);
                customer.setPhoneNumber(Test_phone);
                return customer;
            }else if (invocation.getArgument(0).equals(TEST_Username1)) {
                Customer customer = new Customer();
                customer.setPassword(TEST_Password1);
                customer.setUsername(TEST_Username1);
                customer.setEmail(Test_Email1);
                customer.setPhoneNumber(Test_phone1);
                return customer;
            }  else {
                return null;
            }
        });
        //mock for delete
        lenient().when(customerRepository.deleteCustomerByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TEST_Username)) {
                return 1;
            } else {
                return 0;
            }
        });

        lenient().when(billRepository.findBillsByCustomer(any(Customer.class))).thenAnswer((InvocationOnMock invocation) -> {
            //case1, customer with paid bills
            if (((Customer) invocation.getArgument(0)).getUsername().equals(TEST_Username)) {
                ArrayList<Bill> bills=new ArrayList<>() ;
                Bill bill1=new Bill();
                bill1.setCustomer(((Customer) invocation.getArgument(0)));
                bill1.setBillNo(0);
                bill1.setIsPaid(true);
                bill1.setAmount(10);
                bills.add(bill1);
                return bills;
            }
            //case2, customer with unpaid bills
            else if (((Customer) invocation.getArgument(0)).getUsername().equals(TEST_Username1)) {
                ArrayList<Bill> bills=new ArrayList<>() ;
                Bill bill1=new Bill();
                bill1.setCustomer(((Customer) invocation.getArgument(0)));
                bill1.setBillNo(0);
                bill1.setIsPaid(false);
                bill1.setAmount(10);
                bills.add(bill1);
                return bills;
            }  else {
                return null;
            }
        });
        //mock up for save
        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParam);
    }
    @AfterEach
    public void clearDataBase(){
        customerRepository.deleteAll();
    }
    @Test
    public void test_create_a_timeSlot_successfully() {
        String username = "username1";
        String password = "password1";
        String email = "672202408@qq.com";
        String phone = "12345678";

        // initialize account to null, so we can see if account creation was successful
        Customer customer = null;

        try {
            customer = customerService.CreatAccount(username, password,email,phone );
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(customer);
        assertEquals(username, customer.getUsername());
        assertEquals(password, customer.getPassword());
        assertEquals(email, customer.getEmail());
        assertEquals(password, customer.getPassword());
    }
    @Test
    public void test_create_a_customer_with_existed_username() {
        String username = TEST_Username;
        String password = "password1";
        String email = "672202408@qq.com";
        String phone = "12345678";

        // initialize account to null, so we can see if account creation was successful
        Customer customer = null;
        String error="";
        try {
            customer = customerService.CreatAccount(username, password,email,phone);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(customer);
        assertEquals("The username already exists", error);
    }
    @Test
    public void test_create_a_customer_with_null_username() {
        String username = null;
        String password = "password1";
        String email = "672202408@qq.com";
        String phone = "12345678";

        // initialize account to null, so we can see if account creation was successful
        Customer customer = null;
        String error="";
        try {
            customer = customerService.CreatAccount(username, password,email,phone);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(customer);
        assertEquals("The user name cannot be empty", error);
    }
    @Test
    public void test_create_a_customer_with_short_password() {
        String username = "usermame1";
        String password = "password";
        String email = "672202408@qq.com";
        String phone = "12345678";
        // initialize account to null, so we can see if account creation was successful
        Customer customer = null;
        String error="";
        try {
            customer = customerService.CreatAccount(username, password,email,phone);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(customer);
        assertEquals("The password cannot be less than 8 characters", error);
    }
    @Test
    public void test_create_a_customer_with_invalid_email() {
        String username = "usermame1";
        String password = "password1";
        String email = "672202408qq.com";
        String phone = "12345678";
        // initialize account to null, so we can see if account creation was successful
        Customer customer = null;
        String error="";
        try {
            customer = customerService.CreatAccount(username, password,email,phone);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(customer);
        assertEquals("The email is not valid", error);
    }
    @Test
    public void test_create_a_customer_with_no_phonenumber() {
        String username = "usermame1";
        String password = "password1";
        String email = "672202408@qq.com";
        String phone = "";
        // initialize account to null, so we can see if account creation was successful
        Customer customer = null;
        String error="";
        try {
            customer = customerService.CreatAccount(username, password,email,phone);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(customer);
        assertEquals("The phone number is not valid", error);
    }
    @Test
    public void test_update_a_customer_successfully() {
        String username = TEST_Username;
        String password = "password2";
        String email = "672202408@qq.com";
        String phone = "12345678";

        // initialize account to null, so we can see if account creation was successful
        Customer customer = null;
        String error="";
        try {
            customer = customerService.updateAccount(username, password,email,phone);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(customer);
        assertEquals(username, customer.getUsername());
        assertEquals(password, customer.getPassword());
        assertEquals(email, customer.getEmail());
        assertEquals(password, customer.getPassword());
    }

    @Test
    public void test_update_a_customer_with_none_existed_username() {
        String username = "username";
        String password = "password2";
        String email = "672202408@qq.com";
        String phone = "12345678";

        // initialize account to null, so we can see if account creation was successful
        Customer customer = null;
        String error="";
        try {
            customer = customerService.updateAccount(username, password,email,phone);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(customer);
        assertEquals("The username doesn't exist", error);
    }
    @Test
    public void test_get_a_customer_with_existed_username() {
        String username = TEST_Username;

        // initialize account to null, so we can see if account creation was successful
        Customer customer = null;
        String error="";
        try {
            customer = customerService.getCustomer(username);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNotNull(customer);
        assertEquals(username, customer.getUsername());
        assertEquals(TEST_Password,customer.getPassword());
    }
    @Test
    public void test_get_a_customer_with_none_existed_username() {
        String username = "username1";

        // initialize account to null, so we can see if account creation was successful
        Customer customer = null;
        String error="";
        try {
            customer = customerService.getCustomer(username);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(customer);
        assertEquals("The username doesn't exist",error);
    }
    @Test
    public void test_delete_a_customer_successfully() {
        String username = TEST_Username;

        // initialize account to null, so we can see if account creation was successful
        Integer i = null;
        String error="";
        try {
            i = customerService.deleteAccount(username);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertEquals(1,i);
    }
    @Test
    public void test_delete_a_customer_with_unpaid_bill() {
        String username = TEST_Username1;

        // initialize account to null, so we can see if account creation was successful
        Integer i = null;
        String error="";
        try {
            i = customerService.deleteAccount(username);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if not null and values are as expected
        assertEquals("You have an unpaid bill",error);
    }
    @Test
    public void test_delete_a_customer_with_none_exsited_username() {
        String username = "username1";

        // initialize account to null, so we can see if account creation was successful
        Integer i = null;
        String error="";
        try {
            i = customerService.deleteAccount(username);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertEquals(0,i);
    }


}