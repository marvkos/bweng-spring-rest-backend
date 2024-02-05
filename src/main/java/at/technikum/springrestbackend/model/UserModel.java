package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.apache.catalina.User;
import org.springframework.core.annotation.Order;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    private String id;
    private String username;
    private String pw;
    private String gender;
    private String firstname;
    private String surname;
    private String email;
    private String country;
    private String address;


    protected UserModel() {}

    public UserModel(String id, String username, String pw, String country, String address, String firstname, String surname, String email, String gender) {
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

    public void setAllEntity(String id, String username, String pw, String country, String address, String firstname, String surname, String email, String gender) {
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
