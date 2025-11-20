package com.example.crudapp.api;

import java.util.List;

public interface Service<
        T extends Entity,
        C,
        U
        > {
    List<T> getAll();

    T getById(Long id);

    T save(C createRequest);

    T update(Long id, U updateRequest);

    boolean delete(Long id);
}