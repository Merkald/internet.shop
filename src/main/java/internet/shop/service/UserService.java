package internet.shop.service;

import internet.shop.model.User;
import java.util.List;

public interface UserService {

    User create(User product);

    User get(long id);

    List<User> getAll();

    User update(User newProduct);

    void deleteById(long id);

    void deleteByUser(User product);
}
