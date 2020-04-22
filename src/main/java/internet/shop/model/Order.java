package internet.shop.model;

import internet.shop.storage.Storage;

import java.util.List;
import java.util.Objects;

public class Order {
    private final Long orderId;
    private final List<Item> items;
    private final User user;

    public Order(User user, List<Item> items) {
        orderId = ++Storage.ordersId;
        this.items = items;
        this.user = user;
    }

    public Long getOrderId() {
        return Long.valueOf(orderId);
    }

    public List<Item> getItems() {
        return List.copyOf(items);
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderId().equals(order.getOrderId()) &&
                getItems().equals(order.getItems()) &&
                getUser().equals(order.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getItems(), getUser());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", items=" + items +
                ", user=" + user +
                '}';
    }
}
