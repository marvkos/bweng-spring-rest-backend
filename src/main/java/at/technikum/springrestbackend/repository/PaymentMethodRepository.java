package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, UUID> {

    @Override
    List<PaymentMethod> findAll();
}
