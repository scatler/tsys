package com.scatler.rrweb.dao.interfaces;

import java.util.List;

public interface IDao<T, K> {

    K add(T entity);

    void update(T entity);

    void remove(T entity);

    void removeWith(K id);

    T get(K id);

    List<T> getAll();

    void save(T entity);

}
