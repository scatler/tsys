package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.entity.RouteStation;
import com.scatler.rrweb.service.converter.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("routeStationService")
public class RouteStationService extends AbstractService<RouteStation,RouteStationDTO> {

    @Autowired
    public RouteStationService(IDao<RouteStation, Integer> dao, IConverter<RouteStation, RouteStationDTO> converter) {
        super(dao, converter);
    }

    @Override
    IDao<RouteStation, Integer> getDao() {
        return dao;
    }

    @Override
    IConverter<RouteStation, RouteStationDTO> getConverter() {
        return converter;
    }
}
