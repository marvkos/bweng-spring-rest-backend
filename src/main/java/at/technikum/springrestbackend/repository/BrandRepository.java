package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Brand;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;
import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, UUID> {
     Brand findByname(String name);

     List<Brand> findAll();
     boolean existsByname(String name);

     void deleteBrandByname(String name);
}
