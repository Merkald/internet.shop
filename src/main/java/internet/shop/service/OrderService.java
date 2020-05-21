package internet.shop.service;

import internet.shop.model.Order;
import internet.shop.model.User;
import java.sql.SQLException;
import java.util.List;

public interface OrderService extends GenericService<Order,Long> {
    Order completeOrder(Long userId) throws SQLException;

    List<Order> getUserOrders(User user) throws SQLException;

}
