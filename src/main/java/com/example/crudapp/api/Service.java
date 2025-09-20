package com.example.crudapp.api;

import java.util.List;

public interface Service<T extends Entity> {
    List<T> getAll();
    T getById(Long id);
    void save(T entity);
    void update(T entity);
    void delete(Long id);
}