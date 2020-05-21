package internet.shop.service.impl;

import internet.shop.dao.ShoppingCartDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.service.ShoppingCartService;
import java.sql.SQLException;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart get(Long shoppingCartId) throws SQLException {
        return shoppingCartDao.getById(shoppingCartId).orElseThrow();
    }

    @Override
    public List<ShoppingCart> getAll() throws SQLException {
        return shoppingCartDao.getAll();
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        return shoppingCartDao.deleteById(id);
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) throws SQLException {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) throws SQLException {
        ShoppingCart shoppingCart1 = shoppingCart;
        shoppingCart1.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart1);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) throws SQLException {
        ShoppingCart shoppingCart1 = shoppingCart;
        shoppingCart1.getProducts().remove(product);
        shoppingCartDao.update(shoppingCart1);
        return true;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) throws SQLException {
        ShoppingCart shoppingCart1 = shoppingCart;
        shoppingCart1.getProducts().clear();
        shoppingCartDao.update(shoppingCart1);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) throws SQLException {
        return shoppingCartDao.getByUserId(userId).orElseThrow();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) throws SQLException {
        return shoppingCartDao.getById(shoppingCart.getShoppingCartId())
                .orElseThrow().getProducts();
    }
}
