package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.dao.SpaceRepository;
import ca.mcgill.ecse321.arms.model.Space;
import ca.mcgill.ecse321.arms.model.Technician;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestSpaceService {

    @Mock
    private SpaceRepository spaceRepository;

    @InjectMocks
    private SpaceService spaceService;

    private static int ID = 0;

    @BeforeEach
    public void setMockOutput() {

        // mock for findServiceByName
        lenient().when(spaceRepository.findSpaceBySpaceID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ID)) {
                Space space = new Space();
                space.setSpaceID(ID);
                return space;
            } else {
                return null;
            }
        });

        // mock for findAll
        lenient().when(spaceRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Space space = new Space();
            space.setSpaceID(ID);
            List<Space> list = new ArrayList<Space>();
            list.add(space);
            return list;
        });

        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        // mock for save
        lenient().when(spaceRepository.save(any(Space.class))).thenAnswer(returnParam);
    }

    @After
    public void clearDataBase(){
        spaceRepository.deleteAll();
    }

    @Test
    public void test_create_a_space_successfully(){
        int id = 3;

        Space space = null;

        try{
            space = spaceService.createSpace(id);
        }catch (IllegalArgumentException e){
            fail();
        }

        assertNotNull(space);
        assertEquals(space.getSpaceID(), id);
    }

    @Test
    public void test_create_a_space_already_exists(){
        int id = 0;

        Space space = null;
        String error = "";

        try{
            space = spaceService.createSpace(id);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(space);
        assertEquals(error, "Space " + id + " already exists");
    }

    @Test
    public void test_delete_a_space_successfully(){
        String error = "";

        try{
            spaceService.deleteSpace(0);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "");
    }

    @Test
    public void test_delete_a_space_does_not_exist(){
        String error = "";

        try{
            spaceService.deleteSpace(10);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "Space " + 10 + "was not found");
    }

    @Test
    public void test_get_existing_space(){
        Space space = spaceService.getSpace(0);
        assertEquals(ID, space.getSpaceID());
    }

    @Test
    public void test_get_non_existing_space(){
        String error = "";
        try{
            assertNull(spaceService.getSpace(10));
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error,  "Space " + 10 + "was not found");
    }

    @Test
    public void test_get_all_space(){
        List<Space> spaces = spaceService.getSpaces();
        assertEquals(ID, spaces.get(0).getSpaceID());
    }

}
