package com.scatler.rrweb.dao;

import com.scatler.rrweb.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by alexkpc on 27.07.2019.
 */

@Repository
public class UserDAOImpls implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery <User> cq = cb.createQuery(User.class); //TODO ?
        Root<User> root = cq.from(User.class); //TODO ?
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();

    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public User getUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class,id);
        return user;
    }

    @Override
    public void deleteUser(int id) {

        Session session = sessionFactory.getCurrentSession();
        User deluser = session.byId(User.class).load(id);
        session.delete(deluser);
    }



/*
    public void testMethod(int id) {

        Session session = sessionFactory.getCurrentSession();
        session.createQuery("SELECT FROM Users v");
    }
*/



}
