package internet.shop.dao.impl;

import internet.shop.dao.OrderDao;
import internet.shop.model.Order;
import internet.shop.storage.Storage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order update(Order product) {
        Order order = Storage.orders.get(Math.toIntExact(product.getOrderId()));
        order = product;
        return order;
    }

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
    public List<Order> getUserOrders(Long userId) {
        return Storage.orders.stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
