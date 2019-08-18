package com.scatler.rrweb.dao.impl;

import com.scatler.rrweb.dao.api.AbstractDAO;
import com.scatler.rrweb.entity.RouteStation;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RouteStationDAO extends AbstractDAO<RouteStation> {
    public List<RouteStation> getByRouteId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return (List<RouteStation>) session.createQuery("Select rs from RouteStation rs where rs.routeId.id = :routeId").setParameter("routeId", id).getResultList();
    }
}
