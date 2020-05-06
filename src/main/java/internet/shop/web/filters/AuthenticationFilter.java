package internet.shop.web.filters;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);
    private static final String USER_ID = "user_id";
    private Set<String> allowedUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedUrls.add("/users/login");
        allowedUrls.add("/users/registration");
        allowedUrls.add("/injectData");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getServletPath();
        if (allowedUrls.contains(url)) {
            filterChain.doFilter(req, resp);
            return;
        }
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null) {
            LOGGER.warn("User absent in DB");
            resp.sendRedirect("/users/login");
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
