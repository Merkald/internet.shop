package internet.shop.service;

import internet.shop.model.Bucket;

public interface BucketService {
    Bucket addItem(long bucketId, long ItemId);
}
