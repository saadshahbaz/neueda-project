package ca.mcgill.ecse321.arms.dto;

public class CustomerDto {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    public CustomerDto(String username,String password,String email,String phoneNumber){
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=password;
        this.username=username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
