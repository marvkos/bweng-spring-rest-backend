package at.technikum.springrestbackend.repository;

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

    List<Phone> findByBrand(String brand);

    @Override
    List<Phone> findAll();

}
