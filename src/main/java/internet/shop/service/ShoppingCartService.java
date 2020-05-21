package internet.shop.service;

import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import java.sql.SQLException;
import java.util.List;

public interface ShoppingCartService extends GenericService<ShoppingCart,Long> {
    public ShoppingCart create(ShoppingCart shoppingCart) throws SQLException;

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) throws SQLException;

    boolean deleteProduct(ShoppingCart shoppingCart, Product product) throws SQLException;

    void clear(ShoppingCart shoppingCart) throws SQLException;

    ShoppingCart getByUserId(Long userId) throws SQLException;

    List<Product> getAllProducts(ShoppingCart shoppingCart) throws SQLException;

}
