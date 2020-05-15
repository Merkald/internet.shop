package internet.shop.dao.jdbcimpl;

import internet.shop.dao.ProductDao;
import internet.shop.exeptions.DataProcessingException;
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
    private static final int ID_COLUMN = 1;
    private static final Logger LOGGER = Logger.getLogger(ProductDaoJdbcImpl.class);

    @Override
    public Product update(Product newProduct) {
        String query = "UPDATE products "
                + "SET product_name = ?, product_price = ? "
                + "WHERE product_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newProduct.getName());
            statement.setBigDecimal(2, newProduct.getPrice());
            statement.setLong(3, newProduct.getProductId());
            statement.executeUpdate();
            return newProduct;
        } catch (SQLException ex) {
            LOGGER.error("Cant UPDATE product IN mySQL", ex);
            throw new DataProcessingException("Cant UPDATE product IN mySQL", ex);
        }
    }

    @Override
    public Product create(Product product) {
        String query = "INSERT INTO products"
                + " (product_name, product_price) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                Long productId = resultSet.getLong(ID_COLUMN);
                product.setProductId(productId);
            }
            return product;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant INSERT product IN mySQL", ex);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT * FROM products WHERE product_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Optional.ofNullable(getProductsFromResultSet(resultSet));
        } catch (SQLException ex) {
            LOGGER.error("Cant SELECT user with id:"
                    + id + " ALL FROM mySQL", ex);
            throw new DataProcessingException("Cant SELECT user with id:"
                    + id + " ALL FROM mySQL", ex);
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Product> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getProductsFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            LOGGER.error("Cant SELECT ALL FROM mySQL", ex);
            throw new DataProcessingException("Cant SELECT ALL FROM mySQL", ex);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        deleteRelations("DELETE FROM shopping_carts_products "
                + "WHERE product_id=?;", id);
        deleteRelations("DELETE FROM orders_products "
                + "WHERE product_id=?;", id);
        deleteRelations("DELETE FROM products "
                + "WHERE product_id=?;", id);
        return true;
    }

    private boolean deleteRelations(String query, Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cant DELETE product IN mySQL", ex);
        }
    }

    public Product getProductsFromResultSet(ResultSet resultSet) throws SQLException {
        long productId = resultSet.getLong("product_id");
        String productName = resultSet.getString("product_name");
        BigDecimal productPrice = resultSet.getBigDecimal("product_price");
        Product product = new Product(productName, productPrice);
        product.setProductId(productId);
        return product;
    }
}
