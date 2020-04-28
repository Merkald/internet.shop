package internet.shop.controllers.order;

import internet.shop.lib.Injector;
import internet.shop.service.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("asdasdsadsad");
        req.setAttribute("orders", orderService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/orders/all.jsp").forward(req, resp);
    }
}
