package internet.shop.controllers;

import internet.shop.lib.Injector;
import internet.shop.model.ShoppingCart;
import internet.shop.service.ShoppingCartService;
import internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateShopCartController extends HttpServlet {
    private static final Long USER_ID = 0L;
    private static final Injector injector = Injector.getInstance("internet.shop");
    private ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);
    UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShoppingCart shoppingCart = new ShoppingCart(userService.get(USER_ID));
        shoppingCartService.create(shoppingCart);
        resp.sendRedirect("http://localhost:8080/shoppingCart");
    }
}
