package ca.mcgill.ecse321.arms.dto;

import ca.mcgill.ecse321.arms.model.Assistant;

public class AssistantDto {
    private String username;
    private String password;
    public AssistantDto(String username, String password){
        this.password=password;
        this.username=username;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
