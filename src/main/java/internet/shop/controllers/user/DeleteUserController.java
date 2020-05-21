package internet.shop.controllers.user;

import internet.shop.exeptions.DataProcessingException;
import internet.shop.lib.Injector;
import internet.shop.service.UserService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        try {
            userService.deleteById(userId);
        } catch (SQLException throwables) {
            throw new DataProcessingException("SQL Error ", throwables);
        }
        resp.sendRedirect(req.getContextPath() + "/users/all;");
    }
}
