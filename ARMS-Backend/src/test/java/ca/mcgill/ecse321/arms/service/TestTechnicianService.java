package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.dao.TechnicianRepository;
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

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestTechnicianService {

    @Mock
    private TechnicianRepository technicianRepository;

    @InjectMocks
    private TechnicianService technicianService;

    private static String NAME = "TestTechnician";
    private static int ID = 0;
    private static String EMAIL = "technician@mail.ca";

    @BeforeEach
    public void setMockOutput() {

        // mock for findServiceByName
        lenient().when(technicianRepository.findTechnicianByTechnicianID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ID)) {
                Technician technician = new Technician();
                technician.setTechnicianID(ID);
                technician.setEmail(EMAIL);
                technician.setName(NAME);
                return technician;
            } else {
                return null;
            }
        });

        // mock for findAll
        lenient().when(technicianRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Technician technician = new Technician();
            technician.setTechnicianID(ID);
            technician.setEmail(EMAIL);
            technician.setName(NAME);
            List<Technician> list = new ArrayList<Technician>();
            list.add(technician);
            return list;
        });

        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        // mock for save
        lenient().when(technicianRepository.save(any(Technician.class))).thenAnswer(returnParam);
    }

    @AfterEach
    public void clearDataBase(){
        technicianRepository.deleteAll();
    }

    @Test
    public void test_create_a_technician_successfully(){
        String name = "John Doe";
        int id = 3;
        String email = "john.doe@mail.mcgill.ca";

        Technician technician = null;

        try{
            technician = technicianService.createTechnician(name, email, id);
        }catch (IllegalArgumentException e){
            fail();
        }

        assertNotNull(technician);
        assertEquals(id, technician.getTechnicianID());
        assertEquals(email, technician.getEmail());
        assertEquals(name, technician.getName());
    }

    @Test
    public void test_create_a_technician_without_name(){
        String name = "";
        int id = 3;
        String email = "john.doe@mail.mcgill.ca";

        Technician technician = null;
        String error = "";

        try{
            technician = technicianService.createTechnician(name, email, id);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(technician);
        assertEquals(error, "You must enter a name\n");
    }

    @Test
    public void test_create_a_technician_with_invalid_email(){
        String name = "John Doe";
        int id = 3;
        String email = "john.doe";

        Technician technician = null;
        String error = "";

        try{
            technician = technicianService.createTechnician(name, email, id);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(technician);
        assertEquals(error, "Invalid email");
    }

    @Test
    public void test_create_a_technician_that_already_exists(){
        String name = "John Doe";
        int id = 0;
        String email = "john.doe@mail.mcgill.ca";

        Technician technician = null;
        String error = "";

        try{
            technician = technicianService.createTechnician(name, email, id);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(technician);
        assertEquals(error, "Technician " + 0 + " already exists");
    }

    @Test
    public void test_update_a_technician_successfully(){
        String name = "John Doe";
        int id = 0;
        String email = "john.doe@mail.mcgill.ca";

        Technician technician = null;

        try{
            technician = technicianService.updateTechnician(id, name, email);
        }catch (IllegalArgumentException e){
            fail();
        }

        assertNotNull(technician);
        assertEquals(id, technician.getTechnicianID());
        assertEquals(email, technician.getEmail());
        assertEquals(name, technician.getName());
    }

    @Test
    public void test_update_a_technician_without_specifying_id(){
        String name = "";
        int id = 0;
        String email = "john.doe@mail.mcgill.ca";

        Technician technician = null;
        String error = "";

        try{
            technician = technicianService.updateTechnician(id, name, email);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(technician);
        assertEquals(error, "You must enter a name\n");
    }

    @Test
    public void test_update_a_technician_with_invalid_email(){
        String name = "John Doe";
        int id = 0;
        String email = "john.doe";

        Technician technician = null;
        String error = "";

        try{
            technician = technicianService.updateTechnician(id, name, email);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(technician);
        assertEquals(error, "Invalid email");
    }

    @Test
    public void test_update_a_technician_does_not_exist(){
        String name = "John Doe";
        int id = 10;
        String email = "john.doe@mail.mcgill.ca";

        Technician technician = null;
        String error = "";

        try{
            technician = technicianService.updateTechnician(id, name, email);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(technician);
        assertEquals(error, "The ID given does not exist");
    }

    @Test
    public void test_delete_a_technician_successfully(){
        int id = 0;
        String error = "";

        try{
            technicianService.deleteTechnician(id);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertEquals(error, "");
    }

    @Test
    public void test_delete_a_technician_does_not_exist(){
        int id = 10;
        String error = "";

        try{
            technicianService.deleteTechnician(id);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertEquals(error, "Technician " + 10 + " was not found");
    }

    @Test
    public void test_get_existing_technician(){
        Technician technician = technicianService.getTechnician(0);
        assertEquals(NAME, technician.getName());
    }

    @Test
    public void test_get_non_existing_technician(){
        String error = "";
        try{
            assertNull(technicianService.getTechnician(10));
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "Technician " + 10 + " was not found");
    }

    @Test
    public void test_get_all_technicians(){
        List<Technician> technicians = technicianService.getAllTechnicians();
        assertEquals(technicians.get(0).getName(), NAME);
        assertEquals(technicians.get(0).getTechnicianID(), ID);
        assertEquals(technicians.get(0).getEmail(), EMAIL);
    }

}
