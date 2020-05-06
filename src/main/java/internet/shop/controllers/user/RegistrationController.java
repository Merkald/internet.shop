package internet.shop.controllers.user;

import internet.shop.lib.Injector;
import internet.shop.model.Role;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import internet.shop.service.ShoppingCartService;
import internet.shop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService) INJECTOR
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        int age = Integer.valueOf(req.getParameter("age"));
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");
        if (password.equals(passwordRepeat)) {
            User user = new User(firstName, lastName, age, login, email, password);
            user.setRole(Set.of(Role.of("USER")));
            userService.create(user);
            shoppingCartService.create(new ShoppingCart(user));
            resp.sendRedirect(req.getContextPath() + "/users/all");
        } else {
            LOGGER.error("Passwords are not sames");
            req.setAttribute("message", "passwords are not sames!");
            req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
        }
    }
}
