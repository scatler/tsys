package com.scatler.rrweb.dao;

import com.scatler.rrweb.entity.objects.searchresult.AvailableTrain;
import com.scatler.rrweb.entity.objects.searchresult.StationTimeTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class TicketDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<AvailableTrain> getAvailableTrains(int station_id1, int station_id2, Date day) {

        Session session = sessionFactory.getCurrentSession();


       String hql = "select t from Train t " +
                "inner join t.trainRouteStationList trs " +
                "inner join trs.routeStationId rs " +
                "inner join rs.stationId sid " +
                "inner join rs.routeId r where sid.id = 1001 and trs.id in (select trs2.id from TrainRouteStation trs2 inner join trs2.routeStationId rs2 inner join rs2.stationId st2 where st2.id = 1013 and rs.id > rs2.id)";

/*        String hql = "select trs2.id from TrainRouteStation trs2 inner join trs2.routeStationId rsid2 inner join rsid2.stationId st2 where st2.id = 1013";*/
        List<AvailableTrain> availableTrains = session.createQuery(hql).getResultList();
        System.out.println(availableTrains);
        return null;
    }
}
