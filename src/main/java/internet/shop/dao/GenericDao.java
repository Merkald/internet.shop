package internet.shop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T,K> {
    T update(T product);

    T create(T product);

    Optional<T> get(K id);

    List<T> getAll();

    boolean deleteById(K id);
}
