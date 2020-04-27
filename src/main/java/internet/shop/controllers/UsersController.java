package internet.shop.controllers;

import internet.shop.lib.Injector;
import internet.shop.model.User;
import internet.shop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("internet.shop");
    UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //generateUsers(userService,5);
        List<User> allUsers = userService.getAll();
        req.setAttribute("users", allUsers);
        req.getRequestDispatcher("/WEB-INF/views/users/all.jsp").forward(req,resp);
    }

    public static void generateUsers(UserService userService, int amount) {
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
