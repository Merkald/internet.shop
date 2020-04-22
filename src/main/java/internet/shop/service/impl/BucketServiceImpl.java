package internet.shop.service.impl;

import internet.shop.dao.BucketDao;
import internet.shop.dao.ItemDao;
import internet.shop.model.Bucket;
import internet.shop.model.Item;
import internet.shop.service.BucketService;

import java.util.NoSuchElementException;

public class BucketServiceImpl implements BucketService {
    private BucketDao bucketDao;
    private ItemDao itemDao;
    @Override
    public Bucket addItem(long bucketId, long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Item item = itemDao.get(itemId).orElseThrow(() -> new NoSuchElementException("Cant find item with ID: " + itemId));
        bucket.getItems().add(item);
        return bucketDao.update(bucket);
    }
}
