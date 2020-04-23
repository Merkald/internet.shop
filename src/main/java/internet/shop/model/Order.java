package internet.shop.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private Long orderId;
    private final List<Product> products;
    private final User user;

    public Order(User user, List<Product> products) {
        this.products = products;
        this.user = user;
    }

    public Long getOrderId() {
        return Long.valueOf(orderId);
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return getOrderId().equals(order.getOrderId())
                && getProducts().equals(order.getProducts())
                && getUser().equals(order.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getProducts(), getUser());
    }

    @Override
    public String toString() {
        return "Order{"
                + "orderId=" + orderId
                + ", items=" + products
                + ", user=" + user
                + '}';
    }
}
