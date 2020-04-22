package internet.shop.dao;

import internet.shop.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {
    //CRUD
    Item create(Item item);

    Optional<Item> get(long id);

    List<Item> getAll();

    Item update(Long itemId, Item newItem);

    void deleteById(long id);

    void deleteByItem(Item item);
}
