package ca.mcgill.ecse321.arms.service;
import ca.mcgill.ecse321.arms.ArmsApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.arms.model.*;
import ca.mcgill.ecse321.arms.dao.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssistantService {
    @Autowired
    private AssistantRepository assistantRepository;

    /**
     * create an assistant
     * @param username
     * @param password
     * @return
     * @author Zhiwei Li
     * @throws IllegalArgumentException
     */
    @Transactional
    public Assistant CreatAccount(String username, String password) throws IllegalArgumentException {
        String error = "";
        if (username == null || username.isEmpty()) {
            error = "The user name cannot be empty";
        } else if (password.length()<=8) {
            error = "The password cannot be less than 8 characters";
        } else if(assistantRepository.findAssistantByUsername(username)!=null){
            error = "The username already exists";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        Assistant assistant = new Assistant();
        assistant.setPassword(password);
        assistant.setUsername(username);
        assistantRepository.save(assistant);
        return assistant;
    }

    /**
     * get an assistant with the input username
     * @param username
     * @return
     * @author Zhiwei Li
     * @throws IllegalArgumentException
     */
    @Transactional
    public Assistant getAssistant(String username) throws IllegalArgumentException{
        String error = "";
        if (username == null || username.isEmpty()) {
            error = "The user name cannot be empty";
        } else if(assistantRepository.findAssistantByUsername(username)==null){
            error = "The username doesn't exist";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        return assistantRepository.findAssistantByUsername(username);
    }

    /**
     * update the assistant account with the new username and password
     * @param username
     * @param password
     * @return new assistant
     * @author Zhiwei Li
     */
    @Transactional
    public Assistant updateAccount(String username,String password){
        String error = "";
        if (username == null || username.isEmpty()) {
            error = "The user name cannot be empty";
        } else if (password.length()<=8) {
            error = "The password cannot be less than 8 characters";
        } else if(assistantRepository.findAssistantByUsername(username)==null){
            error = "The username doesn't exist";
        } else if(!ArmsApplication.getCurrentuser().getUsername().equals(username)){
            error = "You can only change your own account";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        Assistant assistant = assistantRepository.findAssistantByUsername(username);
        assistant.setPassword(password);
        assistantRepository.save(assistant);
        return assistant;
    }

    /**
     * delete assistant account with the given username
     * @param username
     * @return
     * @author Zhiwei Li
     */
    @Transactional
    public Integer deleteAccount(){
        Integer i = assistantRepository.deleteAssistantByUsername(ArmsApplication.getCurrentuser().getUsername());
        ArmsApplication.setCurrentuser(null);
        return i;
    }


}
