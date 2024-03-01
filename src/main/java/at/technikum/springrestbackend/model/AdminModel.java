/*package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class AdminModel extends UserModel {
    private String adminType;

    protected AdminModel(){};
    public AdminModel(String userId, String username, String password, String country, String address, String firstname, String surname, String email, String adminType) {
        super(userId, username, password, country, address, firstname, surname, email);
        this.adminType = adminType;
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }
}
*/