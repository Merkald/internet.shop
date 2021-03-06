package internet.shop.dao.impl;

import internet.shop.dao.UserDao;
import internet.shop.model.User;
import internet.shop.storage.Storage;
import java.util.List;
import java.util.Optional;

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
    public Optional<User> findByLogin(String login) {
        return Storage.users.stream().filter(s -> s.getLogin().equals(login)).findFirst();
    }

    @Override
    public boolean deleteById(Long id) {
        boolean result = Storage.users.stream()
                .filter(item -> item.getUserId().equals(id)).findFirst().isEmpty();
        Storage.users.removeIf(item -> item.getUserId().equals(id));
        return result;
    }
}
