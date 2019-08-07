package com.scatler.rrweb.dao;

import com.scatler.rrweb.entity.Train;
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

    public List<Train> getAvailableTrains(int station_id1, int station_id2, Date day) {

        Session session = sessionFactory.getCurrentSession();

        String hql = "select t, tk from Train t " +
                "inner join t.trainRouteidDayList trd " +
                "inner join trd.routeId rs1 " +
                "inner join trd.ticketList tk " +
                "inner join rs1.stationId sid " +
                "inner join rs1.routeId rid where rs1.stationId = '1001' and rs1.routeId in " +
                "(select rs2.routeId from RouteStation rs2 where rs2.stationId = '1001') and rs1.id < " +
                "(select rs3.id from RouteStation rs3 where rs3.stationId = '1013' and rs3.routeId = rs1.routeId)" +
                " and date(trd.dayDept + rs1.day) = '2019-08-05'" +
                "and tk.station1Id = '1001' and tk.trd.id = trd.id ";

        List obj = session.createQuery(hql).getResultList();
        System.out.println(obj);
        return obj;
    }

}
