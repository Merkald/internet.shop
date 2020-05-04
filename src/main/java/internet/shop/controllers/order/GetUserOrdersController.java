package internet.shop.controllers.order;

import internet.shop.lib.Injector;
import internet.shop.model.User;
import internet.shop.service.OrderService;
import internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserOrdersController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        User user = userService.get(userId);
        req.setAttribute("user", user);
        req.setAttribute("userOrders", orderService.getUserOrders(user));
        req.getRequestDispatcher("/WEB-INF/views/orders/userAll.jsp").forward(req, resp);
    }
}
