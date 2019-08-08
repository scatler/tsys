package com.scatler.rrweb.dao;

import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.TrainRouteidDay;
import com.scatler.rrweb.entity.objects.searchresult.StationTimeTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Repository
public class StationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<StationTimeTable> getStationSchedule(int station_id, Date day) {

        Session session = sessionFactory.getCurrentSession();

        //TODO how to replace full name of class
        String hql = "select new com.scatler.rrweb.entity.objects.searchresult.StationTimeTable(t.id,rs.arrivalTime) from Train t inner join t.trainRouteidDayList trs inner join trs.routeId rs inner join rs.stationId sid inner join rs.routeId where sid = ?";
        List<StationTimeTable> stations = session.createQuery(hql).setString(0,String.valueOf(station_id)).getResultList();
        return stations;
    }

    public List<Station> getAllStations() {
        Session session = sessionFactory.getCurrentSession();
        List <Station> stationsList = session.createQuery("select s from Station s").getResultList();
        return stationsList;
    }

    public Station getStation(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Station.class,id);
    }

    public TrainRouteidDay getTRD(int id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.get(TrainRouteidDay.class,id);
        return (TrainRouteidDay) obj;
    }
}
