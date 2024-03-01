package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class UserModel {

    @Id
    private String id;
    private String username;
    private String pw;
    private String name;
    private String email;
    private String country;
    private String address;


    protected UserModel() {}

    public UserModel(String id, String username, String pw, String country, String address, String name, String email) {
        this.id = id;
        this.username = username;
        this.pw = pw;
        this.country = country;
        this.address = address;
        this.name = name;
        this.email = email;
    }

    public void setAllEntity(String id, String username, String pw, String country, String address, String name, String email) {
        this.id = id;
        this.username = username;
        this.pw = pw;
        this.country = country;
        this.address = address;
        this.name = name;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
