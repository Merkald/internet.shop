package internet.shop.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Long productId;
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public void setProductId(Long itemId) {
        this.productId = itemId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return productId.equals(product.productId)
                && name.equals(product.name)
                && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, price);
    }

    @Override
    public String toString() {
        return "Item{"
                + "itemId=" + productId
                + ", name='" + name + '\''
                + ", price=" + price
                + '}';
    }
}
