package internet.shop.dao.impl;

import internet.shop.dao.ProductDao;
import internet.shop.lib.Dao;
import internet.shop.model.Product;
import internet.shop.storage.Storage;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        product.setProductId(Storage.itemsId++);
        Storage.PRODUCTS.add(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.PRODUCTS.stream()
                .filter(i -> i.getProductId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.PRODUCTS;
    }

    @Override
    public Product update(Product newProduct) {
        Storage.PRODUCTS.stream()
                .filter(i -> i.getProductId().equals(newProduct.getProductId()))
                .forEach(i -> i = newProduct);
        return newProduct;
    }

    @Override
    public void deleteById(Long id) {
        Storage.PRODUCTS.removeIf(item -> item.getProductId().equals(id));
    }

    @Override
    public void deleteByItem(Product product) {
        deleteById(product.getProductId());
    }
}
