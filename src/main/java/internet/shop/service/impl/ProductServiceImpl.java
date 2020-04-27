package internet.shop.service.impl;

import internet.shop.dao.ProductDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Product;
import internet.shop.service.ProductService;
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
    public Product get(Long id) {
        return productDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant Find Item With Id " + id));
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product newProduct) {
        return productDao.update(newProduct);
    }

    @Override
    public boolean deleteById(Long id) {
        return productDao.deleteById(id);
    }

    @Override
    public boolean deleteByProduct(Product product) {
        return productDao.deleteById(product.getProductId());
    }
}
