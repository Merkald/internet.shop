package internet.shop.dao;

import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart,Long> {

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);
}
