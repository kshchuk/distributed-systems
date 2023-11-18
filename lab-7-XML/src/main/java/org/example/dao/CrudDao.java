package org.example.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T, Id> {
    T create(T entity) throws Exception;

    Optional<T> read(Id id) throws Exception;

    void update(T entity) throws Exception;

    void delete(Id id) throws Exception;
    List<T> findAll() throws Exception;

}
