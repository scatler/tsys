package com.scatler.rrweb.dao.impl;

import com.scatler.rrweb.dao.api.AbstractDAO;
import com.scatler.rrweb.dto.AllPassengersDTO;
import com.scatler.rrweb.dto.forms.AvailableTrain;
import com.scatler.rrweb.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public class TicketDAO extends AbstractDAO<Ticket> {
    @Autowired
    private SessionFactory sessionFactory;
    @PersistenceContext
    private EntityManager em;

    public List<AvailableTrain> getAvailableTrains(int station_id1, int station_id2, Date dayFrom, Date dayTo) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT" +
                "    alt.trainId, " +
                "    alt.trainName, " +
                "    alt.trainRouteDay, " +
                "    alt.routeId, " +
                "    alt.stfrom as station1Id, " +
                "    alt.station1Name, " +
                "    alt.tin as arrivalTimeToStation1, " +
                "    alt.stto as station2Id , " +
                "    alt.station2Name, " +
                "    alt.tout as arrivalTimeToStation2, " +
                "    (SELECT " +
                "             alt.seats-COUNT(*) " +
                "     FROM " +
                "         alltickets ticks " +
                "     WHERE " +
                "        trd = alt.trainRouteDay and (" +
                "        tin < alt.tout AND tout > alt.tin " +
                "        OR tin = alt.tin " +
                "        OR tout = alt.tout)) as freeTickets, " +
                "    alt.seats as totalSeats " +
                "FROM " +
                "    alltrains alt " +
                "WHERE " +
                "        stfrom = ? AND stto = ? " +
                "  AND arrival_day between ? and ? " +
                "  HAVING freeTickets > 0";
        @SqlResultSetMapping(name = "AvailableTrain", classes = {
                @ConstructorResult(targetClass = AvailableTrain.class,
                        columns = {
                                @ColumnResult(name = "trainId"),
                                @ColumnResult(name = "trainName", type = String.class),
                                @ColumnResult(name = "trainRouteDay", type = Integer.class),
                                @ColumnResult(name = "routeId"),
                                @ColumnResult(name = "station1Id", type = Integer.class),
                                @ColumnResult(name = "station1Name"),
                                @ColumnResult(name = "arrivalTimeToStation1", type = Date.class),
                                @ColumnResult(name = "station2Id"),
                                @ColumnResult(name = "station2Name"),
                                @ColumnResult(name = "arrivalTimeToStation2"/*,type = Date.class*/),
                                @ColumnResult(name = "freeTickets"),
                                @ColumnResult(name = "totalSeats")
                        }
                )
        })
        @Entity
        class SQLMappingCfgEntity {
            @Id
            int id;
        }
        Object resultList = session.createNativeQuery(hql, "AvailableTrain")
                .setParameter(1, station_id1)
                .setParameter(2, station_id2)
                .setDate(3, dayFrom)
                .setDate(4, dayTo)
                .getResultList();
        return (List<AvailableTrain>) resultList;
    }

    public boolean findSamePassenger(Ticket ticket) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select t from Ticket t " +
                "where t.birthday = :date " +
                "and t.name = :name " +
                "and t.surname = :surname")
                .setParameter("name", ticket.getName())
                .setParameter("surname", ticket.getSurname())
                .setDate("date", ticket.getBirthday())
                .getResultList().size() > 0;
    }

    public boolean checkEnoughTimeBeforeDeparture(Ticket ticket) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery(
                "select trd.id  from train_routeid_day trd " +
                        "join route_station rs on trd.route_id = rs.route_id " +
                        "where rs.station_id = ? " +
                        "and trd.id = ? " +
                        "and timestampdiff(minute, now(), timestamp(timestampadd(day,rs.day,trd.day_dept),rs.arrival_time)) > 10");
        query.setParameter(1, ticket.getStation1Id().getId());
        query.setParameter(2, ticket.getTrd().getId());
        return query.getResultList().size() == 0;
    }

    public List<AllPassengersDTO> getAllPassengers(Integer id, Date day) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select distinct " +
                "new com.scatler.rrweb.dto.AllPassengersDTO(" +
                "t.id," +
                "t.name," +
                "t.surname," +
                "date(t.birthday)," +
                "t.station1Id.name," +
                "t.station2Id.name " +
                ") " +
                "from Ticket t " +
                "join TrainRouteidDay trd on t.trd.id = trd.id " +
                "join Route r on r.id = trd.routeId.id " +
                "where r.id = :route " +
                "and trd.dayDept=:date";
        Query query = session.createQuery(
                hql)
                .setParameter("route", id)
                .setParameter("date", day, TemporalType.DATE);
        List resultList = query.getResultList();
        return resultList;
    }

    public boolean checkForFreeSeats(Ticket ticket) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT " +
                "    count(*), " +
                "    (SELECT " +
                "             alt.seats-COUNT(*) " +
                "     FROM " +
                "         alltickets ticks " +
                "     WHERE " +
                "        trd = alt.trainRouteDay and (" +
                "        tin < alt.tout AND tout > alt.tin " +
                "        OR tin = alt.tin " +
                "        OR tout = alt.tout)) as freeTickets, " +
                "    alt.seats as totalSeats " +
                "FROM " +
                "    alltrains alt " +
                "WHERE " +
                "        stfrom = ? AND stto = ? " +
                "  AND alt.trainRouteDay = ? " +
                "  HAVING freeTickets > 0 ";
        List<?> result = session.createNativeQuery(sql)
                .setParameter(1, ticket.getStation1Id().getId())
                .setParameter(2, ticket.getStation2Id().getId())
                .setParameter(3, ticket.getTrd().getId())
                .getResultList();
        return result.size() == 0;
    }
}
