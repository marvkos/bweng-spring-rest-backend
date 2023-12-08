package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Brand;
import at.technikum.springrestbackend.model.Phone;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;

public interface PhoneRepository extends CrudRepository<Phone, UUID> {

    List<Phone> findByName(String name);

    List<Phone> findByDisplaySize(float displaySize);

    List<Phone> findByMemory(int memory);

    List<Phone> findByBattery(int battery);

    List<Phone> findByPrice(float price);

    List<Phone> findByBrand(Brand brand);
    void deletePhoneById(UUID id);

    @Override
    List<Phone> findAll();


}
