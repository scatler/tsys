package com.scatler.rrweb.dao;

import com.scatler.rrweb.entity.StationSchedule;
import com.scatler.rrweb.entity.Stations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Repository
public class StationDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public List<StationSchedule> getStationSchedule(int id, Date day) {

        Session session = sessionFactory.getCurrentSession();

        List stations =  session.createQuery("from Trains inner join TrainsConfig").list();

        System.out.println(stations);
        System.out.println("done");
        return null;
    }
}
