package internet.shop.service;

import internet.shop.model.Product;
import java.util.List;

public interface ProductService {

    Product create(Product product);

    Product get(long id);

    List<Product> getAll();

    Product update(Product newProduct);

    void deleteById(long id);

    void deleteByProduct(Product product);
}
