package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Brand;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, UUID> {
     Brand findByname(String name);

     List<Brand> findAll();
     boolean existsByname(String name);

     void deleteBrandByname(String name);
     Brand save(Brand brand);
     @Modifying
     @Query("UPDATE Brand b SET b.name = :newName,  b.picturePath = :newPicturePath WHERE b.name = :oldName")
      int updateBrandInfo(@Param("oldName") String oldname, @Param("newName") String newUsername, @Param("newPicturePath") String newPicturePath);

}
