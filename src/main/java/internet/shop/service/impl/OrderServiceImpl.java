package internet.shop.service.impl;

import internet.shop.dao.OrderDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Order;
import internet.shop.model.Product;
import internet.shop.model.User;
import internet.shop.service.OrderService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    OrderDao orderDao;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        Order order = new Order(user, products);
        return orderDao.completeOrder(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).orElseThrow();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
