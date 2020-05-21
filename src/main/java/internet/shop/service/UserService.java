package internet.shop.service;

import internet.shop.model.User;
import java.sql.SQLException;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {
    User create(User user) throws SQLException;

    User update(User newProduct) throws SQLException;

    boolean deleteByUser(User user) throws SQLException;

    Optional<User> findByLogin(String login) throws SQLException;
}
