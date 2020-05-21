package internet.shop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T,K> {
    T update(T product) throws SQLException;

    T create(T product) throws SQLException;

    Optional<T> getById(K id) throws SQLException;

    List<T> getAll() throws SQLException;

    boolean deleteById(K id) throws SQLException;
}
