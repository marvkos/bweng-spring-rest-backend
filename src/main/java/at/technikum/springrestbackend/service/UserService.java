package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){this.userRepository = userRepository;}

    public List<User> getUsers(){return userRepository.findAll();}

    public User getUser(UUID id){return userRepository.findById(id).orElseThrow();}

    public List<User> getUsersUsername(String username){return userRepository.findByUsername(username);}

    public List<User> getUserRole(int role){return userRepository.findByRole(role);}

    public List<User> getUserFirstname(String firstname){return userRepository.findByFirstname(firstname);}

    public List<User> getUserLastname(String lastname){return userRepository.findByLastname(lastname);}

    public List<User> getUserEmail(String email){return userRepository.findByEmail(email);}

    public List<User> getUserCountry(String country){return userRepository.findByCountry(country);}

    public List<User> findByStatus(boolean status){return userRepository.findByStatus(status);}

}
