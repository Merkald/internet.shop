package internet.shop.model;

import internet.shop.storage.Storage;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private int count;
    private Long itemId;
    private String name;
    private BigDecimal price;

    public Item(String name, BigDecimal price) {
        count = 0;
        itemId = Storage.itemsId++;
        this.name = name;
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getItemId() {
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
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return itemId.equals(item.itemId) &&
                name.equals(item.name) &&
                price.equals(item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "count=" + count +
                ", itemId=" + itemId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}' + "\n";
    }
}
