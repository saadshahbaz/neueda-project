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


@ExtendWith(MockitoExtension.class)
public class TestAssistantService {
    @Mock
    private AssistantRepository assistantRepository;

    @InjectMocks
    private AssistantService assistantService;

    private static final String TEST_Username = "TestUsername";
    private static final String TEST_Password = "Testpassword";

    @BeforeEach
    public void setMockOutput() {

        // mock for findAssistantByUsername
        lenient().when(assistantRepository.findAssistantByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TEST_Username)) {
                Assistant assistant = new Assistant();
                assistant.setPassword(TEST_Password);
                assistant.setUsername(TEST_Username);
                return assistant;
            } else {
                return null;
            }
        });

        //mock for delete
        lenient().when(assistantRepository.deleteAssistantByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TEST_Username)) {
                return 1;
            } else {
                return 0;
            }
        });
        //mock up for save
        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(assistantRepository.save(any(Assistant.class))).thenAnswer(returnParam);
    }
    @AfterEach
    public void clearDataBase(){
        assistantRepository.deleteAll();
    }
    @Test
    public void test_create_a_assistant_successfully() {
    String username = "username1";
    String password = "password1";

    // initialize account to null, so we can see if account creation was successful
    Assistant assistant = null;

    try {
        assistant = assistantService.CreatAccount(username, password);
    } catch (IllegalArgumentException e) {
        fail();
    }

    // check if not null and values are as expected
    assertNotNull(assistant);
    assertEquals(username, assistant.getUsername());
    assertEquals(password, assistant.getPassword());
    }
    @Test
    public void test_create_a_assistant_with_existed_username() {
        String username = TEST_Username;
        String password = "password1";

        // initialize account to null, so we can see if account creation was successful
        Assistant assistant = null;
        String error="";
        try {
            assistant = assistantService.CreatAccount(username, password);
        } catch (IllegalArgumentException e) {
           error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(assistant);
        assertEquals("The username already exists", error);
    }
    @Test
    public void test_create_a_assistant_with_null_username() {
        String username = null;
        String password = "password1";

        // initialize account to null, so we can see if account creation was successful
        Assistant assistant = null;
        String error="";
        try {
            assistant = assistantService.CreatAccount(username, password);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(assistant);
        assertEquals("The user name cannot be empty", error);
    }
    @Test
    public void test_create_a_assistant_with_short_password() {
        String username = "usermame1";
        String password = "password";

        // initialize account to null, so we can see if account creation was successful
        Assistant assistant = null;
        String error="";
        try {
            assistant = assistantService.CreatAccount(username, password);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(assistant);
        assertEquals("The password cannot be less than 8 characters", error);
    }
    @Test
    public void test_update_a_assistant_successfully() {
        String username = TEST_Username;
        String password = "password2";

        // initialize account to null, so we can see if account creation was successful
        Assistant assistant = null;
        String error="";
        try {
            assistant = assistantService.updateAccount(username, password);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(assistant);
        assertEquals(username, assistant.getUsername());
        assertEquals(password, assistant.getPassword());
    }

    @Test
    public void test_update_a_assistant_with_none_existed_username() {
        String username = "username";
        String password = "password2";

        // initialize account to null, so we can see if account creation was successful
        Assistant assistant = null;
        String error="";
        try {
            assistant = assistantService.updateAccount(username, password);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(assistant);
        assertEquals("The username doesn't exist", error);
    }
    @Test
    public void test_get_a_assistant_with_existed_username() {
        String username = TEST_Username;

        // initialize account to null, so we can see if account creation was successful
        Assistant assistant = null;
        String error="";
        try {
            assistant = assistantService.getAssistant(username);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNotNull(assistant);
        assertEquals(username, assistant.getUsername());
        assertEquals(TEST_Password,assistant.getPassword());
    }
    @Test
    public void test_get_a_assistant_with_none_existed_username() {
        String username = "username1";

        // initialize account to null, so we can see if account creation was successful
        Assistant assistant = null;
        String error="";
        try {
            assistant = assistantService.getAssistant(username);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }

        // check if not null and values are as expected
        assertNull(assistant);
        assertEquals("The username doesn't exist",error);
    }
    @Test
    public void test_delete_a_assistant_successfully() {
        String username = TEST_Username;

        // initialize account to null, so we can see if account creation was successful
        Integer i = null;
        String error="";
        try {
            i = assistantService.deleteAccount(username);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertEquals(1,i);
    }
    @Test
    public void test_delete_a_assistant_with_none_exsited_username() {
        String username = "username1";

        // initialize account to null, so we can see if account creation was successful
        Integer i = null;
        String error="";
        try {
            i = assistantService.deleteAccount(username);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertEquals(0,i);
    }


}