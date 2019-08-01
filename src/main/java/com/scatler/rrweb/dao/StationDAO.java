package com.scatler.rrweb.dao;

import com.scatler.rrweb.entity.StationSchedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by alexkpc on 01.08.2019.
 */

@Repository
public class StationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public StationSchedule getStationSchedule(String day) {

        Session session = sessionFactory.getCurrentSession();

        session.createQuery("SELECT v FROM Users v", StationSchedule.class).getResultList();

        return null;
    }
}
