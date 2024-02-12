package at.technikum.springrestbackend.services;
import at.technikum.springrestbackend.dto.UserDTO;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.UserModel;
import at.technikum.springrestbackend.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean idExists(String id){
        return userRepository.existsById(id);
    }
    public UserModel find(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityExistsException("User not found with id: " + id));
    }

    public List<UserModel> findAll (){
        return userRepository.findAll();
    }

    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }

    public UserModel update(String id, UserDTO userDTOupdate){

        //catching the case when an entity with the id does not exist
        if (!idExists(id)){
            throw new EntityNotFoundException("User with provided ID [" + id + "] not found.");
        }

        //get the existing User from the DB and THEN set new values
        UserModel existingUser = userRepository.findById(id).orElseThrow();

        existingUser.setAllEntity(
                id,
                userDTOupdate.getUsername(),
                userDTOupdate.getPassword(),
                userDTOupdate.getCountry(),
                userDTOupdate.getAddress(),
                userDTOupdate.getFirstname(),
                userDTOupdate.getSurname(),
                userDTOupdate.getEmail());

        return userRepository.save(existingUser);
    }
}
