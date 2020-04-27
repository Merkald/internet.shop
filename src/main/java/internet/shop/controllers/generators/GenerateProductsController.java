package internet.shop.controllers.generators;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.service.ProductService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenerateProductsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("internet.shop");
    ProductService productService = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        generateItems(productService,10);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    public static void generateItems(ProductService productService, int amount) {
        for (int i = 0; i < amount; i++) {
            productService.create(new Product("Item: " + i, new BigDecimal(i)));
        }
    }
}
