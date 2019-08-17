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

    public List<StationTimeTable> getStationSchedule(int station_id, Date day) {

        Session session = sessionFactory.getCurrentSession();
        String hql =
                "select new com.scatler.rrweb.dto.forms.StationTimeTable(" +
                        "t.id," +
                        "rs.routeId.id," +
                        "r.name," +
                        "rs.arrivalTime " +
                        ")" +
                        "from Train t " +
                "join TrainRouteidDay trd on trd.trainId.id = t.id " +
                "inner join trd.routeId rs " +
                "inner join rs.stationId sid " +
                "inner join rs.routeId " +
                "join Route r on r.id = rs.routeId.id " +
                "where sid.id = :station " +
                "and adddays(trd.dayDept,rs.day)=:date";

        List<StationTimeTable> resultList = session.createQuery(hql)
                .setParameter("station", station_id)
                .setDate("date", day)
                .getResultList();
        return resultList;

    }

}
