package com.cg.tutorial.service;

import java.util.List;

public interface IService<T> {
    Iterable<T> findAll();
    void save(T object);
    T findById(long id);
    void remove(long id);
}
