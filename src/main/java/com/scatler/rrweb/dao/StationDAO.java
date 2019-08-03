package com.scatler.rrweb.dao;


import com.scatler.rrweb.entity.Stations;
import com.scatler.rrweb.entity.Stationschedule;
import com.scatler.rrweb.entity.objects.StationTimeTable;
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


    public List<StationTimeTable> getStationSchedule(int id, Date day) {

        Session session = sessionFactory.getCurrentSession();

        //TODO how to replace full name of class
        List<StationTimeTable> stt = session.createQuery("select new com.scatler.rrweb.entity.objects.StationTimeTable(t.id,rl.arrivalTime) from Trains t inner join t.trainsConfigList tcfg inner join tcfg.trainsDaysConfigList trdays inner join tcfg.routesConfigList rcfg inner join rcfg.routesList rl inner join rl.stations").getResultList();

        System.out.println(stt.size());
        System.out.println("done");
        return stt;
    }

    public List<Stations> getAllStations() {
        Session session = sessionFactory.getCurrentSession();
        List <Stations> stationsList = session.createQuery("select s from Stations s").getResultList();
        System.out.println("Selecting stations");
        System.out.println(stationsList.size());
        return stationsList;
    }
}
