package internet.shop.controllers.product;

import internet.shop.exeptions.DataProcessingException;
import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.service.ProductService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductsForAdminController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private ProductService productService = (ProductService) INJECTOR
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> allProducts = null;
        try {
            allProducts = productService.getAll();
        } catch (SQLException throwables) {
            throw new DataProcessingException("SQL Error ", throwables);
        }
        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/views/products/adminAll.jsp").forward(req,resp);
    }
}
