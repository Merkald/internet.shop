package internet.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {
    private Long shoppingCartId;
    private List<Product> items;
    private User user;

    public ShoppingCart(User user) {
        items = new ArrayList<>();
        this.user = user;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public List<Product> getProducts() {
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
                && getProducts().equals(shoppingCart.getProducts())
                && getUser().equals(shoppingCart.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShoppingCartId(), getProducts(), getUser());
    }

    @Override
    public String toString() {
        return "ShoppingCart{"
                + "shoppingCartId=" + shoppingCartId
                + ", items=" + items
                + ", user=" + user
                + '}';
    }
}
