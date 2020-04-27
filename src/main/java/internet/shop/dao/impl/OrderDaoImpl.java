package internet.shop.dao.impl;

import internet.shop.dao.OrderDao;
import internet.shop.lib.Dao;
import internet.shop.model.Order;
import internet.shop.model.User;
import internet.shop.storage.Storage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders.stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.orders.removeIf(order -> order.getOrderId().equals(id));
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders.stream()
                .filter(o -> o.getUser().equals(user))
                .collect(Collectors.toList());
    }
}
