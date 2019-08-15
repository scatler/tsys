package com.scatler.rrweb.dao.impls;

import com.scatler.rrweb.entity.User;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO extends AbstractDAO<User> {
    public UserDAO() {
        super(User.class);
    }


    public User findUserByLogin(String login) {

        User result = null;
        try {
            result = (User) sessionFactory.getCurrentSession().createQuery("select u from User u where u.login = :login").setParameter("login", login).getSingleResult();
        } catch (HibernateException e) {
            System.out.println("Exception in hibernate when find by login");
            return null;
        }
        return result;
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
