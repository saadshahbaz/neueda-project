package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;


import ca.mcgill.ecse321.arms.model.Assistant;

public interface AssistantRepository extends CrudRepository<Assistant, String>{
    Assistant findAssistantByUsername(String name);
    Integer deleteAssistantByUsername(String name);
}
