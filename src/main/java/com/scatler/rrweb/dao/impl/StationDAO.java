package com.scatler.rrweb.dao.impl;

import com.scatler.rrweb.dao.api.AbstractDAO;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import org.hibernate.Session;
import org.hibernate.type.DateType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class StationDAO extends AbstractDAO<Station> {
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
                        "join Route r on trd.routeId.id = r.id " +
                        "join RouteStation rs on r.id = rs.routeId.id " +
                        "inner join rs.stationId sid " +
                        "where sid.id = :station " +
                        "and adddays(trd.dayDept,rs.day)=:date";
                        //"and adddays(trd.dayDept,rs.day)='09/09/2019'";
        List<StationTimeTable> resultList = session.createQuery(hql)
                .setParameter("station", station_id)
                .setParameter("date", day, DateType.INSTANCE)
                .getResultList();
        return resultList;
    }
}
