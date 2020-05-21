package internet.shop.service.impl;

import internet.shop.dao.OrderDao;
import internet.shop.dao.ShoppingCartDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Order;
import internet.shop.model.Product;
import internet.shop.model.User;
import internet.shop.service.OrderService;
import internet.shop.service.ShoppingCartService;
import java.sql.SQLException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(Long userId) throws SQLException {
        List<Product> productsClone = shoppingCartDao
                .getByUserId(userId).orElseThrow().getProducts();
        Order order = new Order(userId, productsClone);
        shoppingCartService.clear(shoppingCartDao.getByUserId(userId).orElseThrow());
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) throws SQLException {
        return orderDao.getUserOrders(user.getUserId());
    }

    @Override
    public Order get(Long id) throws SQLException {
        return orderDao.getById(id).orElseThrow();
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return orderDao.getAll();
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        return orderDao.deleteById(id);
    }
}
