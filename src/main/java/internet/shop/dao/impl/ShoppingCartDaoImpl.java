package internet.shop.dao.impl;

import internet.shop.dao.ShoppingCartDao;
import internet.shop.lib.Dao;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.storage.Storage;
import java.util.List;
import java.util.Optional;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return Storage.addShoppingCard(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> get(Long shoppingCartId) {
        return Storage.shoppingCards.stream()
                .filter(shoppingCart -> shoppingCart.getShoppingCartId().equals(shoppingCartId))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCards;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.shoppingCards
                .removeIf(shoppingCart -> shoppingCart.getShoppingCartId().equals(id));
    }

    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        if (Storage.shoppingCards.get(shoppingCart.getShoppingCartId().intValue())
                .getProducts().stream().filter(i -> i.equals(product)).findFirst().isEmpty()) {
            return false;
        }
        Storage.shoppingCards.get(shoppingCart.getShoppingCartId()
                .intValue()).getProducts().remove(product);
        return true;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long id) {
        return Optional.empty();
    }
}
