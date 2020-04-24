package internet.shop.dao;

import internet.shop.model.Order;
import internet.shop.model.User;
import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Order create(Order order);

    List<Order> getUserOrders(User user);

    Optional<Order> get(Long id);

    List<Order> getAll();

    boolean delete(Long id);
}
