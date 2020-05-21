package internet.shop.controllers.order;

import internet.shop.exeptions.DataProcessingException;
import internet.shop.lib.Injector;
import internet.shop.service.OrderService;
import internet.shop.service.ShoppingCartService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private OrderService orderService = (OrderService) INJECTOR
            .getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService) INJECTOR
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("orderId"));
        try {
            orderService.deleteById(id);
        } catch (SQLException throwables) {
            throw new DataProcessingException("SQL Error ", throwables);
        }
        resp.sendRedirect(req.getContextPath() + "/orders/all");
    }
}
