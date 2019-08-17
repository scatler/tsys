package com.scatler.rrweb.dao.impls;

import com.scatler.rrweb.dto.ViewAllTrain;
import com.scatler.rrweb.entity.Train;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainDAO extends AbstractDAO<Train> {
    public List<ViewAllTrain> viewAllTrains() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select distinct new com.scatler.rrweb.dto.ViewAllTrain(tr.id,tr.name,trd.routeId.routeId.id,r.name,tr.seats,trd.dayDept) from Train tr " +
                "join TrainRouteidDay trd on tr.id = trd.trainId.id " +
                "join Route r on r.id = trd.routeId.routeId.id").getResultList();
    }
}
