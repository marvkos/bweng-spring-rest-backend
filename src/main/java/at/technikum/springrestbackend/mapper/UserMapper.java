package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.UserDTO;
import at.technikum.springrestbackend.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public UserDTO toDTO(UserModel userModel) {
        //creating a new DTO of User to assign the values of the Entity to it
        UserDTO newUserDTO = new UserDTO();
        //assigning ID, PW and USERNAME to the DTO by using SETTER
        newUserDTO.setAllDTO(userModel.getId(), userModel.getUsername(),
                             userModel.getPassword(), userModel.getCountry(),
                             userModel.getAddress(), userModel.getFirstname(),
                             userModel.getSurname(),
                             userModel.getEmail());
        return newUserDTO;
    }

    public UserModel toEntity(UserDTO userDTO) {
        //TODO: security with PassWord needed (Hashing?)
        //DataBank entry requires the id as a primary key
        if (userDTO.getId() == null) {
            return new UserModel(
                        UUID.randomUUID().toString(),
                        userDTO.getUsername(), userDTO.getPassword(),
                        userDTO.getCountry(), userDTO.getAddress(),
                        userDTO.getFirstname(),
                        userDTO.getSurname(),
                        userDTO.getEmail());
        }
//      ALTERNATIVELY:
//        if (userDTO.getId() == null) {
//            UserModel newUserModel = new UserModel(
//                          UUID.randomUUID().toString(),
//                          ...
//                      );
//            return newUserModel;
//        }
        return new UserModel(
                    userDTO.getId(),
                    userDTO.getUsername(), userDTO.getPassword(),
                    userDTO.getCountry(), userDTO.getAddress(),
                    userDTO.getFirstname(),
                    userDTO.getSurname(),
                    userDTO.getEmail());
    }



}
