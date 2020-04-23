package internet.shop.dao.impl;

import internet.shop.dao.OrderDao;
import internet.shop.lib.Dao;
import internet.shop.model.Order;
import internet.shop.model.Product;
import internet.shop.model.User;
import internet.shop.storage.Storage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order completeOrder(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders.stream()
                .filter(order -> order.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
