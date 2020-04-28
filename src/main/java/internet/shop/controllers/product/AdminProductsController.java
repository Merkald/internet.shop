package internet.shop.controllers.product;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminProductsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("internet.shop");
    ProductService productService = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> allProducts = productService.getAll();
        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/views/products/adminAll.jsp").forward(req,resp);
    }
}
