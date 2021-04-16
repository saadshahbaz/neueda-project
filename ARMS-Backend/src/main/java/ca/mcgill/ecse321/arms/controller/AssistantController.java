package ca.mcgill.ecse321.arms.controller;
import ca.mcgill.ecse321.arms.ArmsApplication;
import ca.mcgill.ecse321.arms.dao.AssistantRepository;
import ca.mcgill.ecse321.arms.dto.AssistantDto;
import ca.mcgill.ecse321.arms.model.Assistant;
import ca.mcgill.ecse321.arms.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
public class AssistantController {
    @Autowired
    AssistantService assistantService;
    @Autowired
    AssistantRepository assistantRepository;

    @PostMapping(value = {"/assistant", "/assistant/"})
    public AssistantDto createAssistant (
            @RequestParam("username") String username,
            @RequestParam("password") String password
    )throws IllegalArgumentException{
        Assistant assistant=assistantService.CreatAccount(username,  password);
        return convertToDto(assistant );
    }

    @PutMapping(value = {"/loginAssistant", "/loginAssistant/"})
    public AssistantDto login (
            @RequestParam("username") String username,
            @RequestParam("password") String password
    )throws IllegalArgumentException{
        Assistant assistant=assistantService.getAssistant(username);
        if (assistant.getPassword().equals(password)){
            ArmsApplication.setCurrentuser(assistant);
        }else{
            throw new IllegalArgumentException("The password is incorrect");
        }
        return convertToDto(assistant );
    }
    @DeleteMapping(value = {"/logoutAssistant", "/logoutAssistant/"})
    public void logout(){
            ArmsApplication.setCurrentuser(null);
    }

    @PutMapping(value = {"/updateAssistant", "/updateAssistant/"})
    public AssistantDto updateAssistant(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    )throws IllegalArgumentException{
        Assistant assistant = assistantService.updateAccount(username,password);
        return convertToDto(assistant);
    }

    @GetMapping(value = {"/getAssistant", "/getAssistant/"})
    public AssistantDto getAssistant(
            @RequestParam("username") String username
    )throws IllegalArgumentException{
        return convertToDto(assistantService.getAssistant(username));
    }

    @DeleteMapping(value = {"/deleteAssistant", "/deleteAssistant/"})
    public Integer deleteAssistant(
            @RequestParam("username") String username
    )throws IllegalArgumentException{
        return assistantService.deleteAccount();
    }

    public AssistantDto convertToDto(Assistant assistant){
        if(assistant==null){
            throw new IllegalArgumentException("There is no such assistant");
        }
        AssistantDto assistantDto = new AssistantDto(assistant.getUsername(),assistant.getPassword());
        return assistantDto;
    }

}

