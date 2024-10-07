package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.UserDTO;
import at.technikum.springrestbackend.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO toDTO(UserModel userModel) {
        //creating a new DTO of User to assign the values of the Entity to it
        UserDTO newUserDTO = new UserDTO();
        //assigning ID, PW and USERNAME to the DTO by using SETTER
        newUserDTO.setUserID(userModel.getUserID());
        newUserDTO.setAttendingEvents(userModel.getAttendingEvents());
        newUserDTO.setUsername(userModel.getUsername());
        newUserDTO.setEmail(userModel.getEmail());
        newUserDTO.setProfileDescription(userModel.getProfileDescription());
        newUserDTO.setProfilePicture(userModel.getProfilePicture());
        return newUserDTO;
    }

    public UserModel toEntity(UserDTO userDTO) {
        //DataBank entry requires the id as a primary key
        if (userDTO.getUserID() == null) {
            return new UserModel(
                    UUID.randomUUID().toString(),
                    userDTO.getUsername(),
                    passwordEncoder.encode(userDTO.getPassword()),
                    userDTO.getEmail()
            );
        }
//      ALTERNATIVELY:
//        if (userDTO.getUserId() == null) {
//            UserModel newUserModel = new UserModel(
//                          UUID.randomUUID().toString(),
//                          ...
//                      );
//            return newUserModel;
//        }
        return new UserModel(
                    userDTO.getUserID(),
                    userDTO.getUsername(),
                    passwordEncoder.encode(userDTO.getPassword()),
                    userDTO.getEmail());
    }
}
