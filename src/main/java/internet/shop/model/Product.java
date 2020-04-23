package internet.shop.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Long itemId;
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public void setProductId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getProductId() {
        return itemId;
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
        return itemId.equals(product.itemId)
                && name.equals(product.name)
                && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, price);
    }

    @Override
    public String toString() {
        return "Item{"
                + "itemId=" + itemId
                + ", name='" + name + '\''
                + ", price=" + price
                + '}';
    }
}
