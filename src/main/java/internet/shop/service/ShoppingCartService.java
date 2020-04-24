package internet.shop.service;

import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import java.util.List;

public interface ShoppingCartService extends GenericService<ShoppingCart,Long> {

    public ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

}
