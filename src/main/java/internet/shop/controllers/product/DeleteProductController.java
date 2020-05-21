package internet.shop.controllers.product;

import internet.shop.exeptions.DataProcessingException;
import internet.shop.lib.Injector;
import internet.shop.service.ProductService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private ProductService productService = (ProductService) INJECTOR
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("productId"));
        try {
            productService.deleteById(productId);
        } catch (SQLException throwables) {
            throw new DataProcessingException("SQL Error ", throwables);
        }
        resp.sendRedirect(req.getContextPath() + "/products/adminAll;");
    }
}
