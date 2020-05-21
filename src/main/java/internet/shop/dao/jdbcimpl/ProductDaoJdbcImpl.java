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

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    private static final int ID_COLUMN = 1;

    @Override
    public Product update(Product newProduct) throws SQLException {
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
        }
    }

    @Override
    public Product create(Product product) throws SQLException {
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
        }
    }

    @Override
    public Optional<Product> getById(Long id) throws SQLException {
        String query = "SELECT * FROM products WHERE product_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Optional.ofNullable(getProductsFromResultSet(resultSet));
        }
    }

    @Override
    public List<Product> getAll() throws SQLException {
        String query = "SELECT * FROM products;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Product> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getProductsFromResultSet(resultSet));
            }
            return result;
        }
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        deleteRelations("DELETE FROM shopping_carts_products "
                + "WHERE product_id=?;", id);
        deleteRelations("DELETE FROM orders_products "
                + "WHERE product_id=?;", id);
        deleteRelations("DELETE FROM products "
                + "WHERE product_id=?;", id);
        return true;
    }

    private void deleteRelations(String query, Long id) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
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
