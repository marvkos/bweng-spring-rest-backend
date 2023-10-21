package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Order;
import at.technikum.springrestbackend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {
    @Override
    List<Order> findAll();

    List<Order> findByUser(User user);
}
