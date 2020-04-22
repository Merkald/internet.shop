package internet.shop.dao.impl;

import internet.shop.dao.ItemDao;
import internet.shop.lib.Dao;
import internet.shop.model.Item;
import internet.shop.storage.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(long id) {
        return Storage.items.stream()
                .filter(i -> i.getItemId() == id)
                .findFirst();
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }

    @Override
    public Item update(Long itemId, Item newItem) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getItemId().equals(itemId)) {
                Item result = Storage.items.get(i);
                Storage.items.set(i, newItem);
                return result;
            }
        }
        throw new NoSuchElementException("Cant Find Item by Id: " + itemId.toString());
    }

    @Override
    public void deleteById(long id) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getItemId() == id) {
                Storage.items.remove(i);
                return;
            }
        }
        throw new NoSuchElementException("Cant Find Item With Id " + id);
    }

    @Override
    public void deleteByItem(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).equals(item)) {
                Storage.items.remove(item);
                return;
            }
        }
        throw new NoSuchElementException("Cant Find Item " + item.toString());
    }
}
