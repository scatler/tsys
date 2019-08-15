package com.scatler.rrweb.dao.impls;

import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.dto.forms.AvailableTrain;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
                    "t.name," + //1
                    "trd.id," + //2
                    "rs1.routeId.id," +//3
                    "rs1.stationId.id," +//4
                    "rs1.stationId.name," +//5
                    "rs1.arrivalTime ," +//7
                    "adddays(trd.dayDept,rs1.day), " +//8

                    // 9 time when train arrive to 2nd station
                    "(select rs4.arrivalTime  from RouteStation rs4 where stationId.id = :station2 and routeId = rs1.routeId )," +
                    //10  day when train arrive to 2nd station
                    "(select adddays(trd.dayDept,rs6.day)  from RouteStation rs6 where stationId.id = :station2 and routeId = rs1.routeId )," +
                    //11 2nd station itself
                    //parameter without quotes
                    "(select  st.id  from Station st where id = :station2 )," +
                    //12
                    //parameter without quotes
                    "(select  st.name  from Station st where id = :station2 ) ," +
                    //13 freeseats
                    "(select (t.seats - count(*)) as ft from Ticket ti where ti.station1Id.id = :station1 and ti.trd.id = trd.id) " +

                ")" + //end of searchResultObject

                "from Train t " +
                "join TrainRouteidDay trd on t.id = trd.trainId.id  " +
                "join trd.routeId rs1 " +
                "inner join rs1.stationId sid " +
                "inner join rs1.routeId rid where rs1.stationId.id = :station1 and rs1.routeId in " +
                "(select rs2.routeId from RouteStation rs2 where rs2.stationId.id = :station1) and rs1.id < " +
                "(select rs3.id from RouteStation rs3 where rs3.stationId.id = :station2 and rs3.routeId = rs1.routeId)" +
                "and adddays(trd.dayDept,rs1.day) = :date " +
                "and (select (t.seats - count(*)) as ft from Ticket ti where ti.station1Id.id = :station1 and ti.trd.id = trd.id ) > 0" ;

        List obj = session.createQuery(hql)
                .setParameter("station1",station_id1)
                .setParameter("station2",station_id2)
                .setDate("date",day)
               /* .setParameter("date",day);*/
                .getResultList();

        /*System.out.println(obj);*/
        return obj;
    }

    public boolean findSamePassenger(Ticket ticket) {
        Session session = sessionFactory.getCurrentSession();

        return  session.createQuery("select t from Ticket t " +
                "where t.birthday = :date " +
                "and t.name = :name " +
                "and t.surname = :surname")
                .setParameter("name",ticket.getName())
                .setParameter("surname",ticket.getSurname())
                .setDate("date",ticket.getBirthday())
                .getResultList().size()>0;
    }


    public void checkEnoughTimeBeforeDeparture(Ticket toEntity) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select t from Ticket t " +
                "join TrainRouteidDay trd on t.trd.id = trd.id " +
                "join RouteStation rs on trd.routeId.id = rs.routeId.id and rs.stationId.id = t.station1Id.id " +
                "where adddays(trd.dayDept,rs.day) " +
                "and t.");
        return ;
    }
}
