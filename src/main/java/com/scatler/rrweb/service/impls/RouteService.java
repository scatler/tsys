package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.entity.Route;
import com.scatler.rrweb.service.converter.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("routeService")
public class RouteService extends AbstractService<Route, RouteDTO> {

    @Autowired
    public RouteService(IDao<Route, Integer> dao, IConverter<Route, RouteDTO> converter) {
        super(dao, converter);
    }

    @Override
    IDao<Route, Integer> getDao() {
        return dao;
    }

    @Override
    IConverter<Route, RouteDTO> getConverter() {
        return converter;
    }
}
