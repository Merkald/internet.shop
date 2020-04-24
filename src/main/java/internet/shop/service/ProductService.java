package internet.shop.service;

import internet.shop.model.Product;

public interface ProductService extends GenericService<Product, Long> {

    Product create(Product product);

    Product update(Product newProduct);

    boolean deleteByProduct(Product product);
}
