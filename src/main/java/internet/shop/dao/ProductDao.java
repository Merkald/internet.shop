package internet.shop.dao;

import internet.shop.model.Product;

public interface ProductDao extends GenericDao<Product, Long> {

    Product update(Product newProduct);
}
