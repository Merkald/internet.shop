package internet.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {
    private Long shoppingCartId;
    private List<Product> products;
    private Long userId;

    public ShoppingCart(Long userId) {
        products = new ArrayList<>();
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> items) {
        this.products = items;
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
                && getUserId().equals(shoppingCart.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShoppingCartId(), getProducts(), getUserId());
    }

    @Override
    public String toString() {
        return "ShoppingCart{"
                + "shoppingCartId=" + shoppingCartId
                + ", items=" + products
                + ", user=" + userId
                + '}';
    }
}
