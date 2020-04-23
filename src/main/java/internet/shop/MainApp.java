package internet.shop;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.service.ProductService;
import java.math.BigDecimal;

public class MainApp {

    private static Injector injector = Injector.getInstance("internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        generateItems(productService, 8);
        System.out.println(productService.getAll().toString());
        System.out.println(productService.get(3).toString());
        productService.get(3).setName("Apple");
        productService.get(3).setPrice(new BigDecimal(5555));
        productService.update(productService.get(3));
        productService.deleteById(2);
        productService.deleteByProduct(productService.get(6));
        System.out.println(productService.getAll().toString());
    }

    public static void generateItems(ProductService productService, int amount) {
        for (int i = 0; i < amount; i++) {
            productService.create(new Product("Item: " + i, new BigDecimal(i)));
        }
    }
}
