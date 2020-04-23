package internet.shop.service.impl;

import internet.shop.dao.ProductDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Product;
import internet.shop.service.ProductService;
import internet.shop.storage.Storage;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product get(long id) {
        return productDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant Find Item With Id " + id));
    }

    @Override
    public List<Product> getAll() {
        return Storage.PRODUCTS;
    }

    @Override
    public Product update(Product newProduct) {
        return productDao.update(newProduct);
    }

    @Override
    public void deleteById(long id) {
        productDao.deleteById(id);
    }

    @Override
    public void deleteByProduct(Product product) {
        productDao.deleteByItem(product);
    }
}
