package com.example.crudapp.api;

import java.util.List;

public interface DAO<T extends Entity> {
    void save(T entity);

    T findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(Long id);
}
