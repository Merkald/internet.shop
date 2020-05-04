package internet.shop.service;

import internet.shop.model.User;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {
    User create(User user);

    User update(User newProduct);

    boolean deleteByUser(User user);

    Optional<User> findByLogin(String login);
}
