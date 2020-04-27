package internet.shop.dao;

import internet.shop.model.Order;
import internet.shop.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order,Long> {
    List<Order> getUserOrders(User user);
}
