package internet.shop.dao.impl;

import internet.shop.dao.UserDao;
import internet.shop.lib.Dao;
import internet.shop.model.Product;
import internet.shop.model.User;
import internet.shop.storage.Storage;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        return Storage.addUser(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User newUser) {
        Storage.users.stream()
                .filter(i -> i.getUserId().equals(newUser.getUserId()))
                .forEach(i -> i = newUser);
        return newUser;
    }

    @Override
    public void deleteById(Long id) {
        Storage.users.removeIf(item -> item.getUserId().equals(id));
    }

    @Override
    public void deleteByUser(User user) {
        deleteById(user.getUserId());
    }
}
