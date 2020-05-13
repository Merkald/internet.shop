package internet.shop.dao.jdbcimpl;

import internet.shop.dao.ShoppingCartDao;
import internet.shop.exeptions.DataProcessingException;
import internet.shop.lib.Dao;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.util.ConnectionUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    private static final int ID_COLUMN = 1;

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            Long shoppingCartId = shoppingCart.getShoppingCartId();
            Long userId = shoppingCart.getUserId();
            String query = "UPDATE internet_shop.shopping_carts SET user_id=? "
                    + "WHERE shopping_cart_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.setLong(2, shoppingCartId);
            clear(shoppingCartId);
            for (Product product : shoppingCart.getProducts()) {
                query = "INSERT INTO shopping_carts_products(shopping_cart_id, product_id) "
                        + "VALUES (?,?)";
                statement = connection.prepareStatement(query);
                statement.setLong(1, shoppingCartId);
                statement.setLong(2, product.getProductId());
                statement.executeUpdate();
            }
            return shoppingCart;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant create Shopping Cart.", ex);
        }
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
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
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant create Shopping Cart.", ex);
        }
        return null;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_carts "
                    + "JOIN shopping_carts_products "
                    + "ON shopping_carts.shopping_cart_id = "
                    + "shopping_carts_products.shopping_cart_id "
                    + "WHERE shopping_cart_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return getShoppingCartsFromResultSet(resultSet).stream().findFirst();
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant get Shopping Cart.", ex);
        }
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long id){
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_carts "
                    + "WHERE user_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return getShoppingCartsFromResultSet(resultSet).stream().findFirst();
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant get Shopping Cart.", ex);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_carts "
                    + "JOIN shopping_carts_products "
                    + "ON shopping_carts.shopping_cart_id = "
                    + "shopping_carts_products.shopping_cart_id ";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return getShoppingCartsFromResultSet(resultSet);
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant create Shopping Cart.", ex);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            clear(id);
            String query = "DELETE FROM internet_shop.shopping_carts "
                    + "WHERE shopping_cart_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant DELETE shoppingCart IN mySQL", ex);
        }
    }

    private List<ShoppingCart> getShoppingCartsFromResultSet(ResultSet resultSet)
            throws SQLException {
        List<ShoppingCart> result = new ArrayList<>();
        while (resultSet.next()) {
            Long userId = resultSet.getLong("user_id");
            Long shoppingCartId = resultSet.getLong("shopping_cart_id");
            try (Connection connection = ConnectionUtil.getConnection()) {
                String query = "SELECT * FROM shopping_carts_products "
                        + "JOIN products p "
                        + "ON shopping_carts_products.product_id = p.product_id "
                        + "WHERE shopping_cart_id=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, shoppingCartId);
                ResultSet resultSet1 = statement.executeQuery();
                ShoppingCart shoppingCart = new ShoppingCart(userId);
                shoppingCart.setShoppingCartId(shoppingCartId);
                shoppingCart.setProducts(getProductsFromResultSet(resultSet1));
                result.add(shoppingCart);
            }
        }
        return result;
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

    private void clear(Long shoppingCartId) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM shopping_carts_products WHERE shopping_cart_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCartId);
            statement.executeUpdate();
        }
    }
}
