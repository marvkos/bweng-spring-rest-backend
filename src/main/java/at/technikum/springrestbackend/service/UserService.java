package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsersRole(int role) {
        return userRepository.findByRole(role);
    }

    public List<User> getUsersFirstname(String firstname) {
        return userRepository.findByFirstname(firstname);
    }

    public List<User> getUsersLastname(String lastname) {
        return userRepository.findByLastname(lastname);
    }

    public User getUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersCountry(String country) {
        return userRepository.findByCountry(country);
    }

    public List<User> getUsersStatus(boolean status) {
        return userRepository.findByStatus(status);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

}
