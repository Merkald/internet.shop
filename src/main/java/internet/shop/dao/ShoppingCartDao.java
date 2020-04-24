package internet.shop.dao;

import internet.shop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {

    ShoppingCart addShoppingCart(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getShoppingCart(Long shoppingCartId);

    List<ShoppingCart> getAll();

    void clear(ShoppingCart shoppingCart);
}
