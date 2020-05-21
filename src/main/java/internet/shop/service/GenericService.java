package internet.shop.service;

import java.sql.SQLException;
import java.util.List;

public interface GenericService<T, K> {
    T get(K id) throws SQLException;

    List<T> getAll() throws SQLException;

    boolean deleteById(K id) throws SQLException;
}
