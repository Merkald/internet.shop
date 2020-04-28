package internet.shop.controllers.shoppingсart;

import internet.shop.lib.Injector;
import internet.shop.service.ProductService;
import internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductToShopCartController extends HttpServlet {
    private static final Long USER_ID = 0L;
    private static final Injector INJECTOR = Injector.getInstance("internet.shop");
    private ShoppingCartService shoppingCartService = (ShoppingCartService) INJECTOR
            .getInstance(ShoppingCartService.class);
    private ProductService productService = (ProductService) INJECTOR
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("productId"));
        shoppingCartService.addProduct(shoppingCartService.getByUserId(USER_ID),
                productService.get(productId));
        req.setAttribute("productInShopCard", productService.get(productId)
                .getName() + " Was added to Shopping Cart.");
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/products/all.jsp").forward(req, resp);
    }
}
