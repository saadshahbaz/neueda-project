package ca.mcgill.ecse321.arms.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.arms.model.User;

public interface UserRepository extends CrudRepository<User, String>{

}
