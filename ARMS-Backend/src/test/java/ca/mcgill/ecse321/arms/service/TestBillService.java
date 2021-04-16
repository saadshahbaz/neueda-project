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
public class TestBillService {
    @Mock
    private BillRepository billRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private BillService billService;


    private static final String TEST_Username = "TestUsername";
    private static final String TEST_Password = "Testpassword";
    private static final String Test_Email ="672202408@qq.com";
    private static final String Test_phone ="12345678";

    private static final String TEST_Username1 = "TestUsername1";
    private static final String TEST_Password1 = "Testpassword1";
    private static final String Test_Email1 ="6722024081@qq.com";
    private static final String Test_phone1 ="123456781";

    private static final int Test_billNo = 0;
    private static final int Test_amount = 10;
    private static final boolean Test_isPaid=false;
    @BeforeEach
    public void setMockOutput() {

        // mock for findBillByBillNo
        lenient().when(billRepository.findBillByBillNo(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(Test_billNo)) {
                Customer customer = new Customer();
                customer.setPassword(TEST_Password);
                customer.setUsername(TEST_Username);
                customer.setEmail(Test_Email);
                customer.setPhoneNumber(Test_phone);
                Bill bill = new Bill();
                bill.setBillNo(Test_billNo);
                bill.setIsPaid(Test_isPaid);
                bill.setAmount(Test_amount);
                bill.setCustomer(customer);
                return bill;
            } else {
                return null;
            }
        });

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
        lenient().when(billRepository.save(any(Bill.class))).thenAnswer(returnParam);
    }
    @AfterEach
    public void clearDataBase(){
        billRepository.deleteAll();
    }
    @Test
    public void test_create_a_bill_successfully() {
        String username = "TestUsername";
        int billNo = 20;
        int amount = 10;

        // initialize bill to null, so we can see if account creation was successful
        Bill bill = null;

        try {
            bill = billService.createBill(username,billNo,amount);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(bill);
        assertEquals(username, bill.getCustomer().getUsername());
        assertEquals(billNo,bill.getBillNo());
        assertEquals(amount,bill.getAmount());
    }
    @Test
    public void test_create_a_bill_with_none_existed_username() {
        String username = "username";
        int billNo = 20;
        int amount = 10;

        // initialize account to null, so we can see if account creation was successful
        Bill bill = null;
        String error="";
        try {
            bill = billService.createBill(username,billNo,amount);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(bill);
        assertEquals("No customer found", error);
    }
    @Test
    public void test_create_a_bill_with_duplicate_billNo() {
        String username = TEST_Username;
        int billNo = 0;
        int amount = 10;

        // initialize account to null, so we can see if account creation was successful
        Bill bill = null;
        String error="";
        try {
            bill = billService.createBill(username,billNo,amount);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(bill);
        assertEquals("the billNo is already existed", error);
    }

    @Test
    public void test_get_a_bill_by_billNp_successfully() {
        int billNo = 0;
        String error = "";
        Bill bill = null;
        try {
            bill = billService.getBill(billNo);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertEquals(Test_amount,bill.getAmount());
        assertEquals(TEST_Username,bill.getCustomer().getUsername());
        assertEquals(false,bill.isIsPaid());
    }
    @Test
    public void test_get_a_bill_by_billNp_with_none_exsisted_billNo() {
        int billNo = 10;
        String error = "";
        Bill bill = null;
        try {
            bill = billService.getBill(billNo);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(bill);
        assertEquals("The bill cannot be found", error);
    }
    @Test
    public void test_pay_a_bill_successfully() {
        int billNo = 0;
        String error = "";
        Bill bill = null;
        try {
            bill = billService.payBill(billNo);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertEquals(Test_amount,bill.getAmount());
        assertEquals(TEST_Username,bill.getCustomer().getUsername());
        assertEquals(true,bill.isIsPaid());
    }
    @Test
    public void test_pay_a_bill_with_wrong_billNo() {
        int billNo = 10;
        String error = "";
        Bill bill = null;
        try {
            bill = billService.payBill(billNo);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }
        assertEquals("The bill cannot be found",error);
    }
    @Test
    public void test_get_bills_by_customer_successfully() {
        List<Bill> bills=new ArrayList<>();
        String username=TEST_Username;
        String error = "";
        Bill bill = null;
        try {
            bills = billService.getBillsByCustomer(username);
            bill=bills.get(0);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertEquals(10,bill.getAmount());
        assertEquals(TEST_Username,bill.getCustomer().getUsername());
        assertEquals(true,bill.isIsPaid());
        assertEquals(0,bill.getBillNo());
    }
    @Test
    public void test_get_bills_by_customer_with_none_exsisted_username() {
        List<Bill> bills=new ArrayList<>();
        String username="username";
        String error = "";
        Bill bill = null;
        try {
            bills = billService.getBillsByCustomer(username);
        } catch (IllegalArgumentException e) {
           fail();
        }
        assertEquals(0,bills.size());
    }
}