package at.technikum.springrestbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    private String id;
    @NotBlank
    private String username;
    @NotBlank
    private String pw;
    private String gender;
    @NotBlank
    private String firstname;
    @NotBlank
    private String surname;
    @NotBlank
    @Valid
    private String email;
    @NotBlank
    private String country;
    @NotBlank
    private String address;




    public UserDTO() {
    }

    public UserDTO(String id, String username, String pw, String country, String address, String firstname, String surname, String email, String gender) {
        this.id = id;
        this.username = username;
        this.pw = pw;
        this.country = country;
        this.address = address;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
    }


    public void setAllDTO(String id, String username, String pw, String country, String address, String firstname, String surname, String email, String gender) {
        setCountry(country);
        setUsername(username);
        setAddress(address);
        setEmail(email);
        setFirstname(firstname);
        setSurname(surname);
        setGender(gender);
        setPw(pw);
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
