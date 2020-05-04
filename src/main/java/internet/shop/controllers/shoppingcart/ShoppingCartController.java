package internet.shop.controllers.shoppingcart;

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
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private ShoppingCartService shoppingCartService = (ShoppingCartService) INJECTOR
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        List<Product> items = shoppingCartService.getByUserId(userId).getItems();
        req.setAttribute("shoppingCart", shoppingCartService.getByUserId(userId));
        req.getRequestDispatcher("/WEB-INF/views/shoppingCart/shoppingCartItems.jsp")
                .forward(req,resp);
    }
}
