package internet.shop.controllers.generators;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import internet.shop.service.ProductService;
import internet.shop.service.ShoppingCartService;
import internet.shop.service.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Long USER_ID = 0L;
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private ProductService productService = (ProductService) INJECTOR
            .getInstance(ProductService.class);
    private UserService userService = (UserService) INJECTOR
            .getInstance(UserService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService) INJECTOR
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        generateItems(productService, 10);
        generateUsers(userService, 10);
        ShoppingCart shoppingCart = new ShoppingCart(userService.get(USER_ID));
        shoppingCartService.create(shoppingCart);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void generateItems(ProductService productService, int amount) {
        for (int i = 0; i < amount; i++) {
            productService.create(new Product("Item: " + i, new BigDecimal(i)));
        }
    }

    private void generateUsers(UserService userService, int amount) {
        userService.create(new User("q",
                "q", 1, "q", "q", "q"));
        for (int i = 0; i < amount; i++) {
            String firstName = new StringBuilder("Name ").append(i).toString();
            String lastName = new StringBuilder("Family ").append(i).toString();
            String login = new StringBuilder("login ").append(i).toString();
            String email = new StringBuilder("email@").append(i).toString();
            String password = new StringBuilder("pass").append(i).toString();
            userService.create(new User(firstName,
                    lastName, i, login, email, password));
        }
    }
}
