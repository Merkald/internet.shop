package internet.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bucket {
    private Long bucketId;
    private List<Product> products;
    private User user;

    public Bucket(User user) {
        products = new ArrayList<>();
        this.user = user;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
        if (!(o instanceof Bucket)) {
            return false;
        }
        Bucket bucket = (Bucket) o;
        return getBucketId().equals(bucket.getBucketId())
                && getProducts().equals(bucket.getProducts())
                && getUser().equals(bucket.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBucketId(), getProducts(), getUser());
    }
}
