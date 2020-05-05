package internet.shop.web.filters;

import internet.shop.lib.Injector;
import internet.shop.model.Role;
import internet.shop.model.User;
import internet.shop.service.UserService;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private Map<String, List<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/users/all",List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/users/deleteUser",List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/adminAll",List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/createProduct",List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/deleteProduct",List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/all",List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/CreateOrder",List.of(Role.RoleName.USER));
        protectedUrls.put("/products/all",List.of(Role.RoleName.USER));
        protectedUrls.put("/shoppingCart/allProducts",List.of(Role.RoleName.USER));
        protectedUrls.put("/shoppingCart/addProduct",List.of(Role.RoleName.USER));
        protectedUrls.put("/shoppingCart/removeProduct",List.of(Role.RoleName.USER));
        protectedUrls.put("/users/orders",List.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getServletPath();
        if (protectedUrls.get(url) == null) {
            filterChain.doFilter(req, resp);
            return;
        }
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        User user = userService.get(userId);
        if (isAuthorized(user,protectedUrls.get(url))) {
            filterChain.doFilter(req,resp);
            return;
        } else {
            req.getRequestDispatcher("/WEB-INF/views/accesDenied.jsp").forward(req,resp);
            return;
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAuthorized(User user, List<Role.RoleName> authorizedRoles) {
        for (Role.RoleName autorizedRole: authorizedRoles) {
            for (Role userRole: user.getRoles()) {
                if (autorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}