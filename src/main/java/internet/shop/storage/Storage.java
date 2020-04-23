package internet.shop.storage;

import internet.shop.model.Order;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static Long productId = 0L;
    public static Long bucketsId = 0L;
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
}
