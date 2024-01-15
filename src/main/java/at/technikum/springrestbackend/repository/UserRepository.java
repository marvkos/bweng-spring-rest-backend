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

    List<User> findByRole(String role);

    List<User> findByFirstname(String firstname);

    List<User> findByLastname(String lastname);

    User findByEmail(String mail);

    List<User> findByCountryCode(String countryCode);

    List<User> findByStatus(boolean status);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteUserById(UUID id);

    @Override
    List<User> findAll();
    @Modifying
    @Query("UPDATE User u SET u.username = :newUsername,u.password = :newPassword, u.role = :newRole, u.firstname = :newFirstname, u.lastname = :newLastname, u.salutation = :newSalutation, u.email = :newEmail, u.countryCode = :newCountryCode, u.postalCode = :newPostalCode, u.street = :newStreet, u.city = :newCity, u.houseNumber = :newHouseNumber, u.profilePicture = :newProfilePicture, u.salutation = :newSalutation, u.status = :newStatus WHERE u.username = :oldUsername")
    int updateUserInfo(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername,@Param("newPassword") String newPassword,@Param("newRole") String newRole,@Param("newFirstname") String newFirstname, @Param("newLastname") String newLastname, @Param("newSalutation") String newSalutation,
    @Param("newEmail") String newEmail, @Param("newCountryCode") String newCountryCode, @Param("newPostalCode") int newPostalCode, @Param("newStreet") String newStreet, @Param("newCity") String newCity, @Param("newHouseNumber") String newHouseNumber,  @Param("newProfilePicture") String newProfilePicture, @Param("newStatus") boolean newStatus);
}
