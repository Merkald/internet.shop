package internet.shop.dao.jdbcimpl;

import internet.shop.dao.ProductDao;
import internet.shop.lib.Dao;
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
import org.apache.log4j.Logger;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoJdbcImpl.class);

    @Override
    public Product update(Product newProduct) {
        String query = "UPDATE `internet_shop`.`products` "
                + "SET `product_name` = ?, `product_price` = ? "
                + "WHERE (`product_id` = ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newProduct.getName());
            statement.setBigDecimal(2, newProduct.getPrice());
            statement.setLong(3, newProduct.getProductId());
            statement.executeUpdate();
            return newProduct;
        } catch (SQLException ex) {
            LOGGER.error("Cant UPDATE product IN mySQL", ex);
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Product create(Product product) {
        String query = "INSERT INTO `internet_shop`.`products`"
                + " (`product_name`, `product_price`) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            int id = statement.executeUpdate();
            product.setProductId((long) id);
            return product;
        } catch (SQLException ex) {
            LOGGER.error("Cant INSERT product IN mySQL", ex);
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT * FROM products WHERE product_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Product> result = Optional.empty();
            while (resultSet.next()) {
                long productId = resultSet.getLong("product_id");
                String productName = resultSet.getString("product_name");
                BigDecimal productPrice = resultSet.getBigDecimal("product_price");
                Product product = new Product(productName, productPrice);
                product.setProductId(productId);
                result = Optional.of(product);
            }
            return result;
        } catch (SQLException ex) {
            LOGGER.error("Cant SELECT ALL FROM mySQL", ex);
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products";
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
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
        } catch (SQLException ex) {
            LOGGER.error("Cant SELECT ALL FROM mySQL", ex);
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "DELETE  FROM products "
                + "WHERE product_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOGGER.error("Cant DELETE product IN mySQL", ex);
            ex.printStackTrace();
        }
        return false;
    }
}