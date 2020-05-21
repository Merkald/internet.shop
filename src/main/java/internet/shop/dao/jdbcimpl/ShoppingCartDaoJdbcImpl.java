package internet.shop.dao.jdbcimpl;

import internet.shop.dao.ShoppingCartDao;
import internet.shop.lib.Dao;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.util.ConnectionUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    private static final int ID_COLUMN = 1;

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            Long shoppingCartId = shoppingCart.getShoppingCartId();
            Long userId = shoppingCart.getUserId();
            String query = "UPDATE shopping_carts SET user_id=? "
                    + "WHERE shopping_cart_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.setLong(2, shoppingCartId);
            clearShoppingCart(shoppingCartId);
            for (Product product : shoppingCart.getProducts()) {
                query = "INSERT INTO shopping_carts_products(shopping_cart_id, product_id) "
                        + "VALUES (?,?)";
                statement = connection.prepareStatement(query);
                statement.setLong(1, shoppingCartId);
                statement.setLong(2, product.getProductId());
                statement.executeUpdate();
            }
            return shoppingCart;
        }
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO shopping_carts(user_id) VALUE (?)";
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, shoppingCart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                Long shoppingCartId = resultSet.getLong(ID_COLUMN);
                shoppingCart.setShoppingCartId(shoppingCartId);
                return shoppingCart;
            }
        }
        return null;
    }

    @Override
    public Optional<ShoppingCart> getById(Long id) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_carts sc "
                    + "JOIN shopping_carts_products scp "
                    + "ON sc.shopping_cart_id = scp.shopping_cart_id "
                    + "WHERE shopping_cart_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Optional.of(getShoppingCartsFromResultSet(resultSet));
        }
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long id) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_carts "
                    + "WHERE user_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Optional.of(getShoppingCartsFromResultSet(resultSet));
        }
    }

    @Override
    public List<ShoppingCart> getAll() throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_carts sc "
                    + "JOIN shopping_carts_products scp "
                    + "ON sc.shopping_cart_id = scp.shopping_cart_id ";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<ShoppingCart> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getShoppingCartsFromResultSet(resultSet));
            }
            return result;
        }
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            clearShoppingCart(id);
            String query = "DELETE FROM shopping_carts "
                    + "WHERE shopping_cart_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        }
    }

    private ShoppingCart getShoppingCartsFromResultSet(ResultSet resultSet)
            throws SQLException {
        Long userId = resultSet.getLong("user_id");
        Long shoppingCartId = resultSet.getLong("shopping_cart_id");
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_carts_products scp "
                    + "JOIN products p "
                    + "ON scp.product_id = p.product_id "
                    + "WHERE shopping_cart_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCartId);
            ResultSet resultSet1 = statement.executeQuery();
            ShoppingCart shoppingCart = new ShoppingCart(userId);
            shoppingCart.setShoppingCartId(shoppingCartId);
            shoppingCart.setProducts(getProductsFromResultSet(resultSet1));
            return shoppingCart;
        }
    }

    private List<Product> getProductsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Product> result = new ArrayList<>();
        while (resultSet.next()) {
            long productId = resultSet.getLong("product_id");
            String productName = resultSet.getString("product_name");
            BigDecimal productPrice = resultSet.getBigDecimal("product_price");
            Product product = new Product(productName, productPrice);
            product.setProductId(productId);
            result.add(product);
        }
        return result;
    }

    private void clearShoppingCart(Long shoppingCartId) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM shopping_carts_products WHERE shopping_cart_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCartId);
            statement.executeUpdate();
        }
    }
}
