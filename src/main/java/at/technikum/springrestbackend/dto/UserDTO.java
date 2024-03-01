package at.technikum.springrestbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.checkerframework.common.aliasing.qual.Unique;

public class UserDTO {
    private String userId;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String firstname;
    private String surname;
    @Unique
    @NotBlank
    @Valid
    private String email;
    @NotBlank
    @Valid
    private String country;
    @NotBlank
    private String address;

    public UserDTO() {
    }

    public UserDTO(String userID, String username, String password, String country, String address, String firstname, String surname, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.country = country;
        this.address = address;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
    }


    public void setAllDTO(String userId, String username, String password, String country, String address, String firstname, String surname, String email) {
        setCountry(country);
        setUsername(username);
        setAddress(address);
        setEmail(email);
        setFirstname(firstname);
        setSurname(surname);
        setPassword(password);
        setUserId(userId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
