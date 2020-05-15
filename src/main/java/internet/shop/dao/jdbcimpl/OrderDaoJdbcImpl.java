package internet.shop.dao.jdbcimpl;

import internet.shop.dao.OrderDao;
import internet.shop.exeptions.DataProcessingException;
import internet.shop.lib.Dao;
import internet.shop.model.Order;
import internet.shop.model.Product;
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
public class OrderDaoJdbcImpl implements OrderDao {
    private static final int ID_COLUMN = 1;

    @Override
    public List<Order> getUserOrders(Long userId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders WHERE user_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Order> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getOrdersFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant create Shopping Cart.", ex);
        }
    }

    @Override
    public Order update(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE orders "
                    + "SET user_id=? "
                    + "WHERE order_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getOrderId());
            for (Product product : order.getProducts()) {
                query = "INSERT INTO orders_products(order_id, product_id) "
                        + "VALUES (?,?)";
                statement = connection.prepareStatement(query);
                statement.setLong(1, order.getOrderId());
                statement.setLong(2, product.getProductId());
                statement.executeUpdate();
            }
            return order;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant update Shopping Cart.", ex);
        }
    }

    @Override
    public Order create(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO orders(user_id) VALUE (?)";
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                Long orderId = resultSet.getLong(ID_COLUMN);
                order.setOrderId(orderId);
                return update(order);
            }

        } catch (SQLException ex) {
            throw new DataProcessingException("Cant create Shopping Cart.", ex);
        }
        return null;
    }

    @Override
    public Optional<Order> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders WHERE order_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Optional.of(getOrdersFromResultSet(resultSet));
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant create Shopping Cart.", ex);
        }
    }

    @Override
    public List<Order> getAll() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Order> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getOrdersFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant create Shopping Cart.", ex);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            clearOrder(id);
            String query = "DELETE FROM orders "
                    + "WHERE order_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant DELETE shoppingCart IN mySQL", ex);
        }
    }

    private Order getOrdersFromResultSet(ResultSet resultSet)
            throws SQLException {
        Long userId = resultSet.getLong("user_id");
        Long orderId = resultSet.getLong("order_id");
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders_products "
                    + "JOIN products p "
                    + "ON orders_products.product_id = p.product_id "
                    + "WHERE order_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet1 = statement.executeQuery();
            Order order = new Order(userId, getProductsFromResultSet(resultSet1));
            order.setOrderId(orderId);
            return order;
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

    private void clearOrder(Long orderId) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders_products WHERE order_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            statement.executeUpdate();
        }
    }
}
