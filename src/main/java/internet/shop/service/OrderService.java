package internet.shop.service;

import internet.shop.model.Order;
import internet.shop.model.Product;
import internet.shop.model.User;
import java.util.List;

public interface OrderService extends GenericService<Order,Long> {
    Order completeOrder(Long userId);

    List<Order> getUserOrders(User user);

}
