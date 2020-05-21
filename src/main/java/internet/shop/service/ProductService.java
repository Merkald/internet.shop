package internet.shop.service;

import internet.shop.model.Product;
import java.sql.SQLException;

public interface ProductService extends GenericService<Product, Long> {
    Product create(Product product) throws SQLException;

    Product update(Product newProduct) throws SQLException;

    boolean deleteByProduct(Product product) throws SQLException;
}
