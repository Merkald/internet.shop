package internet.shop.dao.impl;

import internet.shop.dao.BucketDao;
import internet.shop.model.Bucket;
import internet.shop.storage.Storage;

import java.util.NoSuchElementException;

public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
        return null;
    }

    @Override
    public Bucket get(Long bucketId) {
        return Storage.buckets.stream()
                .filter(b -> b.getBucketId().equals(bucketId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Cant find Bucket with id " + bucketId));
    }

    @Override
    public Bucket update(Bucket bucket) {
        return null;
    }
}
