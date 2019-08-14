package com.scatler.rrweb.dao.impls;

import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class StationDAO extends AbstractDAO <Station> {
    public StationDAO() {
        super(Station.class);
    }

    public List<StationTimeTable> getStationSchedule(int station_id, Date day) {

        Session session = sessionFactory.getCurrentSession();
        //TODO check object location
        String hql = "select new com.scatler.rrweb.dto.forms.StationTimeTable(t.id,rs.arrivalTime) from Train t " +
                "join TrainRouteidDay trd on trd.trainId.id = t.id " +
                "inner join trd.routeId rs " +
                "inner join rs.stationId sid " +
                "inner join rs.routeId " +
                "where sid = ?";
        return (List<StationTimeTable>) session.createQuery(hql).setString(0,String.valueOf(station_id)).getResultList();
    }

}
