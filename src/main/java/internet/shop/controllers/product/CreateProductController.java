package internet.shop.controllers.product;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.service.ProductService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private ProductService productService = (ProductService) INJECTOR
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/createProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(req.getParameter("price")));
        productService.create(new Product(name, price));
        resp.sendRedirect(req.getContextPath() + "/products/adminAll");
    }
}
