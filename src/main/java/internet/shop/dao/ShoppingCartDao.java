package internet.shop.dao;

import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {

    ShoppingCart addShoppingCart(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getShoppingCart(Long shoppingCartId);

    List<ShoppingCart> getAll();

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);
}
