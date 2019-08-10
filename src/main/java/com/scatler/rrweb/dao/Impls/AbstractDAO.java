package com.scatler.rrweb.dao.Impls;

import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.entity.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AbstractDAO<T extends AbstractEntity> implements IDao<T,Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer add(T entity) {
        return null;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void remove(T entity) {

    }

    @Override
    public void removeWith(Integer id) {

    }

    @Override
    public T get(Integer id) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public void save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);

    }
}
