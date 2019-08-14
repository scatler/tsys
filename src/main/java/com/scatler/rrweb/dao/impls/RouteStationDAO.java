package com.scatler.rrweb.dao.impls;

import com.scatler.rrweb.entity.RouteStation;
import org.springframework.stereotype.Repository;

@Repository
public class RouteStationDAO extends AbstractDAO<RouteStation> {
    public RouteStationDAO() {
        super(RouteStation.class);
    }
}
