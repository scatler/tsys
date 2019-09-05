package com.scatler.rrweb.dao.api;

import com.scatler.rrweb.entity.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractDAO<T extends AbstractEntity> implements IDao<T, Integer> {
    private Class<?> entityClass;

    public AbstractDAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityClass = (Class) pt.getActualTypeArguments()[0];
    }

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void saveAll(List<T> entity) {
        entity.forEach(this::save);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    public Integer addOrUpdate (T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity.getId();
    }

    @Override
    public void remove(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void removeById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.get(entityClass, id);
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public T get(Integer id) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return (List<T>) session.createQuery("from " + entityClass.getName(), entityClass).getResultList();
    }

    @Override
    public void save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public void merge(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
    }
}
