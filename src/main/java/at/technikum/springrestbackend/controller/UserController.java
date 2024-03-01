package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.UserDTO;
import at.technikum.springrestbackend.mapper.UserMapper;
import at.technikum.springrestbackend.model.UserModel;
import at.technikum.springrestbackend.repository.UserRepository;
import at.technikum.springrestbackend.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserMapper userMapper;
    private final UserServices userServices;
    private final UserRepository userRepository;
    @Autowired
    public UserController(
                        UserMapper userMapper,
                        UserServices userServices,
                        UserRepository userRepository) {

        this.userMapper = userMapper;
        this.userServices = userServices;
        this.userRepository = userRepository;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> readAll() {
        return userServices.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
  
    @GetMapping("/{userId}")

    @ResponseStatus(HttpStatus.FOUND)
    public UserDTO read(@PathVariable String userId) {
        UserModel user = userServices.find(userId);
        return userMapper.toDTO(user);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserDTO userDTO){
        UserModel user = userMapper.toEntity(userDTO);
        userServices.save(user);
        return userMapper.toDTO(user);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable String userId, @RequestBody UserDTO updatedUserDTO){

        //for update logic CTRL+LMB on 'update' - method call
        return userMapper.toDTO(userServices.update(userId, updatedUserDTO));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDTO delete(@PathVariable String userId){
        UserDTO deletedUserDTO =
                new UserDTO(
                        userId,
                        userServices.find(userId).getUsername(),
                        userServices.find(userId).getPassword(),
                        userServices.find(userId).getCountry(),
                        userServices.find(userId).getAddress(),
                        userServices.find(userId).getFirstname(),
                        userServices.find(userId).getSurname(),
                        userServices.find(userId).getEmail()
                );

        userServices.find(userId);
        userRepository.deleteById(userId);
        return deletedUserDTO;
    }

}


