package internet.shop.dao;

import internet.shop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart,Long> {

    void clear(ShoppingCart shoppingCart);
}
