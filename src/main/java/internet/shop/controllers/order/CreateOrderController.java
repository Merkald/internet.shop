package internet.shop.controllers.order;

import internet.shop.lib.Injector;
import internet.shop.model.Order;
import internet.shop.model.ShoppingCart;
import internet.shop.service.OrderService;
import internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateOrderController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private OrderService orderService = (OrderService) INJECTOR
            .getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService) INJECTOR
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        Order order = orderService
                .completeOrder(shoppingCart.getProducts(),
                        shoppingCart.getUser());
        shoppingCartService.clear(shoppingCart);
        req.setAttribute("order", order);
        req.getRequestDispatcher("/WEB-INF/views/orders/order.jsp").forward(req, resp);
    }
}
