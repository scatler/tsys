package com.scatler.rrweb.dao.interfaces;

import java.util.List;

public interface IDao<T, K> {

    void update(T entity);

    void remove(T entity);

    void removeById(K id);

    T get(K id);

    List<T> getAll();

    void save(T entity);

    void saveAll (List<T> entity);

    void merge(T entity);

}
