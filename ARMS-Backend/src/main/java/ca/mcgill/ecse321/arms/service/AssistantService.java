package ca.mcgill.ecse321.arms.service;
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

    @Transactional
    public Assistant updateAccount(String username,String password){
        String error = "";
        if (username == null || username.isEmpty()) {
            error = "The user name cannot be empty";
        } else if (password == null || password.isEmpty()) {
            error = "The password cannot be empty";
        } else if(assistantRepository.findAssistantByUsername(username)==null){
            error = "The username doesn't exist";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error.trim());
        }
        Assistant assistant = assistantRepository.findAssistantByUsername(username);
        assistant.setPassword(password);
        assistantRepository.save(assistant);
        return assistant;
    }

    @Transactional
    public Integer deleteAccount(String username){
        return assistantRepository.deleteAssistantByUsername(username);
    }


}
