package internet.shop.controllers.user;

import internet.shop.exeptions.DataProcessingException;
import internet.shop.lib.Injector;
import internet.shop.model.Role;
import internet.shop.model.User;
import internet.shop.service.ShoppingCartService;
import internet.shop.service.UserService;
import java.io.IOException;
import java.sql.SQLException;
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
            User user = new User(firstName, lastName, age, login,
                    email, password);
            user.setRole(Set.of(Role.of("USER")));
            try {
                user = userService.create(user);
            } catch (SQLException throwables) {
                throw new DataProcessingException("SQL Error ", throwables);
            }
            resp.sendRedirect(req.getContextPath() + "/users/all");
        } else {
            LOGGER.error("Passwords don't match!");
            req.setAttribute("message", "Passwords don't match!");
            req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
        }
    }
}
