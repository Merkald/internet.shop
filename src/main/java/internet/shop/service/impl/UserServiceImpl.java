package internet.shop.service.impl;

import internet.shop.dao.OrderDao;
import internet.shop.dao.ShoppingCartDao;
import internet.shop.dao.UserDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Order;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import internet.shop.service.UserService;
import internet.shop.util.HashUtil;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private OrderDao orderDao;

    @Override
    public User create(User user) {
        user.setPassword(HashUtil.hashPassword(user.getPassword(),user.getSalt()));
        User user1 = userDao.create(user);
        ShoppingCart shoppingCart = new ShoppingCart(user.getUserId());
        shoppingCartDao.create(shoppingCart);
        return user1;
    }

    @Override
    public User get(Long id) {
        return userDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant Find User With Id " + id));
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User newUser) {
        return userDao.update(newUser);
    }

    @Override
    public boolean deleteById(Long id) {
        ShoppingCart shoppingCart = shoppingCartDao.getByUserId(id).orElseThrow();
        shoppingCartDao.deleteById(shoppingCart.getShoppingCartId());
        List<Order> orders = orderDao.getUserOrders(id);
        for (Order order : orders) {
            orderDao.deleteById(order.getOrderId());
        }
        return userDao.deleteById(id);
    }

    @Override
    public boolean deleteByUser(User user) {
        ShoppingCart shoppingCart = shoppingCartDao.getByUserId(user.getUserId()).orElseThrow();
        shoppingCartDao.deleteById(shoppingCart.getShoppingCartId());
        List<Order> orders = orderDao.getUserOrders(user.getUserId());
        for (Order order : orders) {
            orderDao.deleteById(order.getOrderId());
        }
        return userDao.deleteById(user.getUserId());
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
