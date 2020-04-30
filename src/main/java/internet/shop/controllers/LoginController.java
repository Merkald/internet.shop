package internet.shop.controllers;

import internet.shop.exeptions.AuthenticationExeption;
import internet.shop.lib.Injector;
import internet.shop.model.User;
import internet.shop.security.AuthenticationService;
import internet.shop.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private AuthenticationService authenticationService = (AuthenticationService) INJECTOR
            .getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = authenticationService.login(login, password);
        } catch (AuthenticationExeption authenticationExeption) {
            req.setAttribute("errorMessage",authenticationExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
