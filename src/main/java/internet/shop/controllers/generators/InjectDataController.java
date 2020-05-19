package internet.shop.controllers.generators;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.model.Role;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import internet.shop.service.ProductService;
import internet.shop.service.ShoppingCartService;
import internet.shop.service.UserService;
import internet.shop.util.HashUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
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
        generateUsers(userService, 10);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void generateItems(ProductService productService, int amount) {
        for (int i = 0; i < amount; i++) {
            productService.create(new Product("Item: " + i, new BigDecimal(i)));
        }
    }

    private void generateUsers(UserService userService, int amount) {
        User user = new User("Boris",
                "Britva", 1, "q", "q", "q");
        user.setRole(Set.of(Role.of("ADMIN")));
        userService.create(user);
        shoppingCartService.create(new ShoppingCart(user.getUserId()));
        user = new User("Petro",
                "Kowbasa", 1, "w", "q", "w");
        user.setRole(Set.of(Role.of("USER")));
        userService.create(user);
        shoppingCartService.create(new ShoppingCart(user.getUserId()));
        for (int i = 0; i < amount; i++) {
            String firstName = new StringBuilder("Name ").append(i).toString();
            String lastName = new StringBuilder("Family ").append(i).toString();
            String login = new StringBuilder("login ").append(i).toString();
            String email = new StringBuilder("email@").append(i).toString();
            String password = new StringBuilder("pass").append(i).toString();
            user = new User(firstName,
                    lastName, i, login, email, password);
            user.setRole(Set.of(Role.of("USER")));
            userService.create(user);
            shoppingCartService.create(new ShoppingCart(user.getUserId()));
        }
    }
}
