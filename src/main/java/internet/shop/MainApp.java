package internet.shop;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import internet.shop.service.OrderService;
import internet.shop.service.ProductService;
import internet.shop.service.ShoppingCartService;
import internet.shop.service.UserService;
import java.math.BigDecimal;

public class MainApp {

    private static Injector injector = Injector.getInstance("internet.shop");

    public static void main(String[] args) {
        testOrder();
    }

    public static void testOrder() {
        UserService userService = (UserService) injector.getInstance(UserService.class);
        ProductService productService = (ProductService) injector
                .getInstance(ProductService.class);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        generateOrder(userService, productService, orderService, 5);
        System.out.println(orderService.getAll());
        orderService.completeOrder(productService.getAll(), userService.get(1));
        System.out.println(orderService.getUserOrders(userService.get(1)));
        orderService.delete(0L);
        orderService.delete(3L);
        System.out.println(orderService.getAll());
    }

    public static void generateOrder(UserService userService,
                                     ProductService productService, OrderService orderService, int amount) {
        generateItems(productService, amount);
        generateUsers(userService, amount);
        for (int i = 0; i < amount; i++) {
            orderService.completeOrder(productService.getAll(), userService.get(i));
        }
    }

    public static void testShoppingCart() {
        UserService userService = (UserService) injector.getInstance(UserService.class);
        ProductService productService = (ProductService) injector
                .getInstance(ProductService.class);
        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        generateShoppingCarts(userService, shoppingCartService, 8);
        System.out.println(shoppingCartService.getAll());
        generateItems(productService, 7);
        addLotOfProducts(6, 5, shoppingCartService, productService);
        System.out.println(shoppingCartService.getShoppingCart(6L));
        System.out.println(shoppingCartService.getAllProducts(shoppingCartService.getShoppingCart(6L)));
        System.out.println(shoppingCartService.getByUserId(7L));
        shoppingCartService.deleteProduct(shoppingCartService.getShoppingCart(6L), productService.get(1));
        System.out.println(shoppingCartService.getAllProducts(shoppingCartService.getShoppingCart(6L)));
        shoppingCartService.clear(shoppingCartService.getShoppingCart(6L));
        System.out.println(shoppingCartService.getShoppingCart(6L));
    }

    public static void addLotOfProducts(long cartId, int amount, ShoppingCartService shoppingCartService,
                                        ProductService productService) {
        for (int i = 0; i < amount; i++) {
            shoppingCartService.addProduct(shoppingCartService.getShoppingCart(cartId), productService.get(i));
        }
    }

    public static void generateShoppingCarts(UserService userService,
                                             ShoppingCartService shoppingCartService, int amount) {
        generateUsers(userService, 10);

        for (int i = 0; i < amount; i++) {
            ShoppingCart shoppingCart = new ShoppingCart(userService.get(i));
            shoppingCartService.addShoppingCart(shoppingCart);
        }
    }

    public static void testUser() {
        UserService userService = (UserService) injector.getInstance(UserService.class);
        generateUsers(userService, 10);
        System.out.println(userService.getAll().toString());
        userService.deleteById(4);
        userService.deleteById(5);
        System.out.println(userService.get(7));
        userService.deleteByUser(userService.get(7));
        User user = userService.get(1);
        user.setPhone(4654654);
        userService.update(user);
        System.out.println(userService.getAll());
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

    public static void testProduct() {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        generateItems(productService, 8);
        System.out.println(productService.getAll().toString());
        System.out.println(productService.get(3).toString());
        productService.get(3).setName("Apple");
        productService.get(3).setPrice(new BigDecimal(5555));
        productService.update(productService.get(3));
        productService.deleteById(2);
        productService.deleteByProduct(productService.get(6));
        System.out.println(productService.getAll().toString());
    }

    public static void generateItems(ProductService productService, int amount) {
        for (int i = 0; i < amount; i++) {
            productService.create(new Product("Item: " + i, new BigDecimal(i)));
        }
    }
}
