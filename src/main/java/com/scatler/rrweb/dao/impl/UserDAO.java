package com.scatler.rrweb.dao.impl;

import com.scatler.rrweb.dao.api.AbstractDAO;
import com.scatler.rrweb.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO extends AbstractDAO<User> {
    public User findUserByLogin(String login) {
        return (User) sessionFactory.getCurrentSession().createQuery("select u from User u where u.login = :login").setParameter("login", login).getSingleResult();
    }

    public User findByEmail(String email) {
        List resultList = sessionFactory.getCurrentSession().createQuery("select u from User u where u.email = :email").setParameter("email", email).getResultList();
        if (resultList.size() > 0) {
            return (User) resultList.get(0);
        } else {
            return null;
        }
    }
}
