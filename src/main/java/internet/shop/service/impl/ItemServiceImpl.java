package internet.shop.service.impl;

import internet.shop.dao.ItemDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Item;
import internet.shop.service.ItemService;
import internet.shop.storage.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(long id) {
        return itemDao.get(id).orElseThrow(() -> new NoSuchElementException("Cant Find Item With Id " + id));
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }

    @Override
    public List<Item> getAllAvailable() {
        return Storage.items.stream().filter(i -> i.getCount()!=0).collect(Collectors.toList());
    }

    @Override
    public Item update(Long itemId, Item newItem) {
        return itemDao.update(itemId, newItem);
    }

    @Override
    public void deleteById(long id) {
        itemDao.deleteById(id);
    }

    @Override
    public void deleteByItem(Item item) {
        itemDao.deleteByItem(item);
    }
}
