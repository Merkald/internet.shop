package internet.shop.service;

import internet.shop.model.Item;

import java.util.List;

public interface ItemService {

    Item create(Item item);

    Item get(long id);

    List<Item> getAll();

    List<Item> getAllAvailable();

    Item update(Long itemId, Item newItem);

    void deleteById(long id);

    void deleteByItem(Item item);
}
