package ca.mcgill.ecse321.arms.dto;


public class TechnicianDto {
    private String name;
    private String email;
    private int id;

    /**
     * @param name
     * @param email
     * @param id
     */
    public TechnicianDto(String name, String email, int id){
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public int getId(){
        return this.id;
    }
}
