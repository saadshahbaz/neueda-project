package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.dao.TechnicianRepository;
import ca.mcgill.ecse321.arms.model.Technician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnicianService {
    @Autowired
    TechnicianRepository technicianRepository;

    /**
     * create a new technician with name, email and id
     * @param name
     * @param email
     * @param ID
     * @throws IllegalArgumentException
     * @return the technician that is created
     * @author Cecila Jiang
     */
    @Transactional
    public Technician createTechnician(String name, String email, int ID){
        // parameter check
        String error = "";
        if(name.equals("")){
            error += "You must enter a name\n";
        }
        if(!isValidEmailAddress(email)){
            error += "Invalid email";
        }
        if(error.length()>0)    throw new IllegalArgumentException(error);

        // check if the ID already exists
        Technician technician = technicianRepository.findTechnicianByTechnicianID(ID);
        if(technician!=null){
            error = "Technician " + ID + " already exists";
            throw new IllegalArgumentException(error);
        }
        Technician newTech = new Technician();
        newTech.setName(name);
        newTech.setTechnicianID(ID);
        newTech.setEmail(email);
        technicianRepository.save(newTech);
        return newTech;
    }

    /**
     * update the info of a technician according to his/her id
     * @param ID the id of the technician you want to update
     * @param name new name
     * @param email new email
     * @throws IllegalArgumentException
     * @return the technician that is updated
     * @author Cecilia Jiang
     */
    @Transactional
    public Technician updateTechnician(int ID, String name, String email){
        // parameter check
        String error = "";
        if(name.equals("")){
            error += "You must enter a name\n";
        }
        if(!isValidEmailAddress(email)){
            error += "Invalid email";
        }
        if(error.length()>0)    throw new IllegalArgumentException(error);

        Technician technician = technicianRepository.findTechnicianByTechnicianID(ID);
        if(technician==null){
            throw new IllegalArgumentException("The ID given does not exist");
        }
        technician.setName(name);
        technician.setEmail(email);
        technicianRepository.save(technician);
        return technician;
    }

    /**
     * delete a technician in the ARMS according to his/her id
     * @param id id of the technician
     * @throws IllegalArgumentException
     * @author Cecilia Jiang
     */
    @Transactional
    public void deleteTechnician(int id){
        Technician technician = technicianRepository.findTechnicianByTechnicianID(id);
        if(technician==null){
            throw new IllegalArgumentException("Technician " + id + " was not found");
        }
        technicianRepository.delete(technician);
    }

    /**
     * get a technician with his/her id
     * @param id
     * @return the technician that was found
     * @author Cecilia Jiang
     */
    @Transactional
    public Technician getTechnician(int id){
        Technician technician = technicianRepository.findTechnicianByTechnicianID(id);
        if(technician==null){
            throw new IllegalArgumentException("Technician " + id + " was not found");
        }
        return technician;
    }

    /**
     * @return all the technicians in the ARMS
     * @author Cecilia Jiang
     */
    @Transactional
    public List<Technician> getAllTechnicians(){
        return toList(technicianRepository.findAll());
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

    private static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
