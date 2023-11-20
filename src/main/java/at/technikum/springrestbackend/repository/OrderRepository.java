package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Orders;
import at.technikum.springrestbackend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Orders, UUID> {
    @Override
    List<Orders> findAll();

    List<Orders> findByUser(User user);
}
