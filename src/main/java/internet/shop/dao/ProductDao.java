package internet.shop.dao;

import internet.shop.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Product create(Product product);

    Optional<Product> get(Long id);

    List<Product> getAll();

    Product update(Product newProduct);

    void deleteById(Long id);

    void deleteByItem(Product product);
}
