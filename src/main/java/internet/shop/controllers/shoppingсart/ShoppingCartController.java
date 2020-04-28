package internet.shop.controllers.shoppingсart;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.service.ShoppingCartService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShoppingCartController extends HttpServlet {
    private static final Long USER_ID = 0L;
    private static final Injector injector = Injector.getInstance("internet.shop");
    private ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> items = shoppingCartService.getByUserId(USER_ID).getItems();
        req.setAttribute("shoppingCartItems", items);
        req.setAttribute("userId",USER_ID);
        req.getRequestDispatcher("/WEB-INF/views/shoppingCart/shoppingCartItems.jsp")
                .forward(req,resp);
    }
}
