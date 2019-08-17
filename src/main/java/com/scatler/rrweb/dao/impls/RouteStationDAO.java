package com.scatler.rrweb.dao.impls;

import com.scatler.rrweb.entity.RouteStation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RouteStationDAO extends AbstractDAO<RouteStation> {
    public List<RouteStation> getByRouteId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return (List<RouteStation>) session.createQuery("Select rs from RouteStation rs where rs.routeId.id = :routeId").setParameter("routeId", id).getResultList();

    }
}
