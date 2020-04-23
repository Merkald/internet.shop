package internet.shop.dao.impl;

import internet.shop.dao.ShoppingCartDao;
import internet.shop.lib.Dao;
import internet.shop.model.Product;
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
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        return Storage.shoppingCards.stream()
                .filter(card -> card.getShoppingCartId().equals(shoppingCart.getShoppingCartId()))
                .peek(card -> card.getItems().add(product)).findFirst().orElseThrow();
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        if (Storage.shoppingCards.get(shoppingCart.getShoppingCartId().intValue())
                .getItems().stream().filter(i -> i.equals(product)).findFirst().isEmpty()) {
            return false;
        }
        Storage.shoppingCards.get(shoppingCart.getShoppingCartId()
                .intValue()).getItems().remove(product);
        return true;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        Storage.shoppingCards
                .get(shoppingCart.getShoppingCartId().intValue()).getItems().clear();
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return Storage.shoppingCards.stream()
                .filter(cart -> cart.getUser().getUserId().equals(userId))
                .findFirst().orElseThrow();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return Storage.shoppingCards.stream()
                .filter(cart -> cart.equals(shoppingCart))
                .findFirst().orElseThrow().getItems();
    }
}
