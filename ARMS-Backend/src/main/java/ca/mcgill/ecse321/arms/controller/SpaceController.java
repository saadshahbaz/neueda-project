package ca.mcgill.ecse321.arms.controller;

import ca.mcgill.ecse321.arms.dto.SpaceDto;
import ca.mcgill.ecse321.arms.model.Space;
import ca.mcgill.ecse321.arms.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class SpaceController {
    @Autowired
    private SpaceService spaceService;

    @PostMapping(value = {"/space", "/space/"})
    public SpaceDto createSpace(
            @RequestParam("id") int id
    ) throws IllegalArgumentException{
        Space space = spaceService.createSpace(id);
        return convertToDto(space);
    }

    @GetMapping(value = {"/allSpace", "/allSpace/"})
    public List<SpaceDto> getAllSpace() throws IllegalArgumentException{
        List<SpaceDto> spaceDtos = new ArrayList<>();
        for(Space space : spaceService.getSpaces()){
            spaceDtos.add(convertToDto(space));
        }
        return spaceDtos;
    }

    @DeleteMapping(value = {"/deleteSpace", "deleteSpace/"})
    public void deleteSpace(
            @RequestParam("id") int id
    ) throws IllegalArgumentException{
        spaceService.deleteSpace(id);
    }

    public static SpaceDto convertToDto(Space space){
        if(space==null){
            throw new IllegalArgumentException("There is no such space");
        }
        SpaceDto spaceDto = new SpaceDto(space.getSpaceID());
        return spaceDto;
    }
}
