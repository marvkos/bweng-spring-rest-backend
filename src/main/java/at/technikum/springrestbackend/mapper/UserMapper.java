package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.UserDTO;
import at.technikum.springrestbackend.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public UserDTO toDTO(UserModel userModel) {
        //creating a new DTO of User
        UserDTO newUserDTO = new UserDTO();
        //assigning ID, PW and USERNAME to the DTO by using SETTER
        newUserDTO.setAllDTO(userModel.getId(),userModel.getUsername(),
                             userModel.getPw(), userModel.getCountry(),
                             userModel.getAddress(), userModel.getFirstname(),
                             userModel.getSurname(), userModel.getEmail(),
                             userModel.getGender()
                            );

        return newUserDTO;
    }

    public UserModel toEntity(UserDTO userDTO) {

        //TODO: security with PassWord needed (Hashing?)
        if (userDTO.getId() == null) {
            //UserModel does not have an empty Constructor
            //--> Object creation requires ID, PW, USERNAME
            return new UserModel(
                        UUID.randomUUID().toString(),
                        userDTO.getUsername(), userDTO.getPw(),
                        userDTO.getCountry(), userDTO.getAddress(),
                        userDTO.getFirstname(), userDTO.getSurname(),
                        userDTO.getEmail(), userDTO.getGender()
            );
        }

//      ALTERNATIVELY:
//        if (userDTO.getId() == null) {
//
//            UserModel newUserModel = new UserModel(
//                          UUID.randomUUID().toString(),
//                          userDTO.getUsername(),
//                          userDTO.getPw(),
//                          ...
//                      );
//
//            return newUserModel;
//        }


        return new UserModel(
                    userDTO.getId(),
                    userDTO.getUsername(), userDTO.getPw(),
                    userDTO.getCountry(), userDTO.getAddress(),
                    userDTO.getFirstname(), userDTO.getSurname(),
                    userDTO.getEmail(), userDTO.getGender()
        );
    }



}
