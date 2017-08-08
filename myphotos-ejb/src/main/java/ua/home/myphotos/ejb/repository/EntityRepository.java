package ua.home.myphotos.ejb.repository;

import java.util.Optional;

/**
 * Created by vov on 07.08.2017.
 */
public interface EntityRepository<T, ID> {
    Optional<T> findById(ID id);

    void create (T entity);

    void update (T entity);

    void delete (T entity);

    void flush();

    T getProxyInstance(ID id);


}
