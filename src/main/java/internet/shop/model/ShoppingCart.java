package internet.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {
    private Long bucketId;
    private List<Product> items;
    private User user;

    public ShoppingCart(User user) {
        items = new ArrayList<>();
        this.user = user;
    }

    public Long getShoppingCartId() {
        return bucketId;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingCart)) {
            return false;
        }
        ShoppingCart shoppingCart = (ShoppingCart) o;
        return getShoppingCartId().equals(shoppingCart.getShoppingCartId())
                && getItems().equals(shoppingCart.getItems())
                && getUser().equals(shoppingCart.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShoppingCartId(), getItems(), getUser());
    }
}
