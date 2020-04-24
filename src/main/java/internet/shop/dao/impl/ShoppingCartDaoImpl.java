package internet.shop.dao.impl;

import internet.shop.dao.ShoppingCartDao;
import internet.shop.lib.Dao;
import internet.shop.model.ShoppingCart;
import internet.shop.storage.Storage;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
        return Storage.addShoppingCard(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getShoppingCart(Long shoppingCartId) {
        return Storage.shoppingCards.stream()
                .filter(shoppingCart -> shoppingCart.getShoppingCartId().equals(shoppingCartId))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCards;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        Storage.shoppingCards
                .get(shoppingCart.getShoppingCartId().intValue()).getItems().clear();
    }
}
