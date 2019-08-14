package com.scatler.rrweb.dao.impls;

import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.dto.forms.AvailableTrain;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class TicketDAO extends AbstractDAO<Ticket> {

    @Autowired
    private SessionFactory sessionFactory;

    public TicketDAO() {
        super(Ticket.class);
    }

    public List<AvailableTrain> getAvailableTrains(int station_id1, int station_id2, Date day) {

        Session session = sessionFactory.getCurrentSession();

        //TODO check object location
        String hql = "select " +
                "new com.scatler.rrweb.dto.forms.AvailableTrain (" + // start of searchResultObject
                    "t," +
                    "trd.id," +
                    "rs1.routeId.id," +
                    "rs1.stationId," +
                    "trd.dayDept, " +
                    "rs1.arrivalTime ," +
                    "rs1.day," +
                    // time when train arrive to 2nd station
                    "(select rs4.arrivalTime  from RouteStation rs4 where stationId = '1013' and routeId = rs1.routeId )," +
                    // day when train arrive to 2nd station
                    "(select rs4.day  from RouteStation rs4 where stationId = '1013' and routeId = rs1.routeId )," +
                    // 2nd station itself
                    "(select st  from Station st where id = 1013 )," +
                    "(select count(*) from Ticket t where t.station1Id = '1001' and t.trd.id = trd.id)" +

                ")" + //end of searchResultObject

                "from Train t " +
                "join TrainRouteidDay trd on t.id = trd.trainId.id  " +
                "join trd.routeId rs1 " +
                "inner join rs1.stationId sid " +
                "inner join rs1.routeId rid where rs1.stationId = '1001' and rs1.routeId in " +
                "(select rs2.routeId from RouteStation rs2 where rs2.stationId = '1001') and rs1.id < " +
                "(select rs3.id from RouteStation rs3 where rs3.stationId = '1013' and rs3.routeId = rs1.routeId)" +
                "and date(trd.dayDept + rs1.day) = '2019-08-05'" ;

        List obj = session.createQuery(hql).getResultList();
        System.out.println(obj);
        return obj;
    }

    public boolean findSamePassenger(Ticket ticket) {
        Session session = sessionFactory.getCurrentSession();

        return  session.createQuery("select t from Ticket t " +
                "where t.birthday = '08/07/2019'"  ).getResultList().size()>0;
    }
}
