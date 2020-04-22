package internet.shop.model;

import internet.shop.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bucket {
    private Long bucketId;
    private List<Item> items;
    private User user;

    public Bucket(User user) {
        bucketId = ++Storage.bucketsId;
        items = new ArrayList<>();
        this.user = user;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
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
        if (this == o) return true;
        if (!(o instanceof Bucket)) return false;
        Bucket bucket = (Bucket) o;
        return getBucketId().equals(bucket.getBucketId()) &&
                getItems().equals(bucket.getItems()) &&
                getUser().equals(bucket.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBucketId(), getItems(), getUser());
    }
}
