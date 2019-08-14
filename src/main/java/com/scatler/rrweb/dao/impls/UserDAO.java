package com.scatler.rrweb.dao.impls;

import com.scatler.rrweb.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends AbstractDAO<User> {
    public UserDAO() {
        super(User.class);
    }


    public User findUserByLogin(String login) {

        return (User) sessionFactory.getCurrentSession().createQuery("select u from User u where u.login = :login").setParameter("login", login).getSingleResult();
    }
}
