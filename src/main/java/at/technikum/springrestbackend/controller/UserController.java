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
    public UserController(UserMapper userMapper,
                          UserServices userServices,
                          UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userServices = userServices;
        this.userRepository = userRepository;
    }
    @GetMapping
    public List<UserDTO> readAll() {
        return userServices.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public UserDTO read(@PathVariable String id) {
        UserModel user = userServices.find(id);
        return userMapper.toDTO(user);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserDTO userDTO){
        UserModel user = userMapper.toEntity(userDTO);
        userServices.save(user);
        return userMapper.toDTO(user);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable String id, @RequestBody UserDTO updatedUserDTO){

        return userMapper.toDTO(userServices.update(id, updatedUserDTO));
    }

    @DeleteMapping("/{id}")
    public UserDTO delete(@PathVariable String id){
        UserDTO deletedUserDTO =
                new UserDTO(
                        id, userServices.find(id).getUsername(),
                        userServices.find(id).getPw(),
                        userServices.find(id).getCountry(),
                        userServices.find(id).getAddress(),
                        userServices.find(id).getFirstname(),
                        userServices.find(id).getSurname(),
                        userServices.find(id).getEmail(),
                        userServices.find(id).getGender()
                );

        userRepository.deleteById(id);
        return deletedUserDTO;
    }

}


