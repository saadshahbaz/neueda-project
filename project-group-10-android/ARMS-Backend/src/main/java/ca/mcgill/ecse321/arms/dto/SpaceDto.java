package ca.mcgill.ecse321.arms.dto;

public class SpaceDto {
    private int id;

    /**
     * @param id
     */
    public SpaceDto(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
