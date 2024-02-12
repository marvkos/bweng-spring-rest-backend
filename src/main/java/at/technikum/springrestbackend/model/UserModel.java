package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    private String id;
    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String email;
    private String country;
    private String address;


    protected UserModel() {}

    public UserModel(String id, String username, String password, String country, String address, String firstname, String surname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.country = country;
        this.address = address;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
    }

    public void setAllEntity(String id, String username, String password, String country, String address,String firstname, String surname, String email) {
        setCountry(country);
        setUsername(username);
        setAddress(address);
        setEmail(email);
        setFirstname(firstname);
        setSurname(surname);
        setPassword(password);
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

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
