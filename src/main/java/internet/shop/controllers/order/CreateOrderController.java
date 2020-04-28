package internet.shop.controllers.order;

import internet.shop.lib.Injector;
import internet.shop.model.Order;
import internet.shop.service.OrderService;
import internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateOrderController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("internet.shop");
    private OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Order order = orderService
                .completeOrder(shoppingCartService
                                .getAllProducts(shoppingCartService.getByUserId(id)),
                        shoppingCartService.getByUserId(id).getUser());
        shoppingCartService.clear(shoppingCartService.get(id));
        System.out.println("List size= " + orderService.getAll().size());
        req.setAttribute("order",order);
        req.getRequestDispatcher("/WEB-INF/views/orders/order.jsp").forward(req,resp);
        //resp.sendRedirect(req.getContextPath() + "/orders/all");
    }
}
