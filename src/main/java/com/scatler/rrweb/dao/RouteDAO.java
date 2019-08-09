package com.scatler.rrweb.dao;

import com.scatler.rrweb.entity.TrainRouteidDay;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RouteDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public TrainRouteidDay getTrainRouteidDay (int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TrainRouteidDay.class,id);

    }
}
