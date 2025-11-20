package com.example.crudapp.api;

import java.util.List;

public interface DAO<T extends Entity> {
    boolean save(T entity);

    T findById(Long id);

    List<T> findAll();

    boolean update(T entity);

    boolean delete(Long id);
}
