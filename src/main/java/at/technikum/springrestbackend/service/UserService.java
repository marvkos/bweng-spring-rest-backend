package at.technikum.springrestbackend.service;


import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByUsername(String username) {
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

    @Transactional
    public void deleteUser(UUID id) {
        userRepository.deleteUserById(id);
    }
    @Transactional
    public int updateUserInfo(String oldUsername, String newUsername,String newPassword, String newRole,String newFirstname, String newLastname, Enum newSalutation, String newEmail, String newAddress,  String newCity, int newPostalcode, String newCountry, String newProfilePicture) {
        return userRepository.updateUserInfo(oldUsername, newUsername,newPassword,newRole,newFirstname,newLastname,newSalutation, newEmail,newAddress,newCity,newPostalcode,newCountry,newProfilePicture);
    }
}
