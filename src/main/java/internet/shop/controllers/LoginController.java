package internet.shop.controllers;

import internet.shop.exeptions.AuthenticationExeption;
import internet.shop.exeptions.DataProcessingException;
import internet.shop.lib.Injector;
import internet.shop.model.User;
import internet.shop.security.AuthenticationService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private AuthenticationService authenticationService = (AuthenticationService) INJECTOR
            .getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = authenticationService.login(login, password);
            HttpSession session = req.getSession();
            session.setAttribute("user_id", user.getUserId());
            session.setAttribute("user",user);
        } catch (AuthenticationExeption authenticationExeption) {
            req.setAttribute("errorMessage", authenticationExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        } catch (SQLException e) {
            throw new DataProcessingException("SQL Error ", e);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
