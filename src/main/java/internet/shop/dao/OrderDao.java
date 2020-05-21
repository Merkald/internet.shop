package internet.shop.dao;

import internet.shop.model.Order;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends GenericDao<Order,Long> {
    List<Order> getUserOrders(Long userId) throws SQLException;
}
