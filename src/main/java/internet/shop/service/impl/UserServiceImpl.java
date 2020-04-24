package internet.shop.service.impl;

import internet.shop.dao.UserDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.User;
import internet.shop.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(long id) {
        return userDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant Find User With Id " + id));
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User newUser) {
        return userDao.update(newUser);
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public void deleteByUser(User user) {
        userDao.deleteById(user.getUserId());
    }
}
