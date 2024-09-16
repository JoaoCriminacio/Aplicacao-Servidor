package com.unimater.dao;

import com.unimater.model.Entity;

import java.util.List;

public interface GenericDAO<T extends Entity> {
    List<T> getAll();
    T getById(int id);
    void upsert(T entity);
    void delete(int id);
}
