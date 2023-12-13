package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    User findByUsername(String username);

    List<User> findByRole(int role);

    List<User> findByFirstname(String firstname);

    List<User> findByLastname(String lastname);

    User findByEmail(String mail);

    List<User> findByCountry(String country);

    List<User> findByStatus(boolean status);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteUserById(UUID id);

    @Override
    List<User> findAll();
    @Modifying
    @Query("UPDATE User u SET u.username = :newUsername,u.password = :newPassword, u.role = :newRole, u.firstname = :newFirstname, u.lastname = :newLastname, u.salutation = :newSalutation, u.email = :newEmail, u.address = :newAddress, u.city = :newCity, u.postalcode = :newPostalcode, u.country = :newCountry, u.profilePicture = :newProfilePicture WHERE u.username = :oldUsername")
    int updateUserInfo(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername,@Param("newPassword") String newPassword,@Param("newRole") String newRole,@Param("newFirstname") String newFirstname, @Param("newLastname") String newLastname, @Param("newSalutation") Enum newSalutation,
    @Param("newEmail") String newEmail, @Param("newAddress") String newAddress, @Param("newCity") String newCity, @Param("newPostalcode") int newPostalcode, @Param("newCountry") String newCountry, @Param("newProfilePicture") String newProfilePicture);
}
