package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.dao.SpaceRepository;
import ca.mcgill.ecse321.arms.model.Space;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpaceService {
    @Autowired
    SpaceRepository spaceRepository;

    /**
     * create a space with id
     * @param ID
     * @return the space that is created
     * @author Cecilia Jiang
     */
    @Transactional
    public Space createSpace(int ID){
        Space foundSpace = spaceRepository.findSpaceBySpaceID(ID);
        if(foundSpace!=null){
            String error = "Space " + ID + " already exists";
            throw new IllegalArgumentException(error);
        }
        Space space = new Space();
        space.setSpaceID(ID);
        spaceRepository.save(space);
        return space;
    }

    /**
     * delete a space according to its id
     * @param ID
     * @author Cecilia Jiang
     */
    @Transactional
    public void deleteSpace(int ID){
        Space foundSpace = spaceRepository.findSpaceBySpaceID(ID);
        if(foundSpace==null){
            String error = "Space " + ID + "was not found";
            throw new IllegalArgumentException(error);
        }
        spaceRepository.delete(foundSpace);
    }

    /**
     * get a space with its id
     * @param id
     * @return the space that was found
     * @author Cecilia Jiang
     */
    @Transactional
    public Space getSpace(int id){
        Space space = spaceRepository.findSpaceBySpaceID(id);
        if(space==null){
            String error = "Space " + id + "was not found";
            throw new IllegalArgumentException(error);
        }
        return space;
    }

    /**
     * @return all the spaces in the ARMS
     */
    @Transactional
    public List<Space> getSpaces(){
        return toList(spaceRepository.findAll());
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
