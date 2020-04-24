package internet.shop.storage;

import internet.shop.model.Order;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static Long productId = 0L;
    public static Long shoppingCardId = 0L;
    public static Long usersId = 0L;
    public static Long ordersId = 0L;
    public static final List<Product> products = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCards = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();

    public static Product addProduct(Product product) {
        product.setProductId(productId++);
        products.add(product);
        return product;
    }

    public static User addUser(User user) {
        user.setUserId(usersId++);
        users.add(user);
        return user;
    }

    public static ShoppingCart addShoppingCard(ShoppingCart shoppingCard) {
        shoppingCard.setShoppingCartId(shoppingCardId++);
        shoppingCards.add(shoppingCard);
        return shoppingCard;
    }

    public static Order addOrder(Order order) {
        order.setOrderId(ordersId++);
        orders.add(order);
        return order;
    }
}
