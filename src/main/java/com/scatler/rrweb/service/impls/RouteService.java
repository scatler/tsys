package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.RouteDAO;
import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.entity.Route;
import com.scatler.rrweb.entity.RouteStation;
import com.scatler.rrweb.service.converter.IConverter;
import com.scatler.rrweb.service.converter.RouteConverter;
import com.scatler.rrweb.service.converter.RouteStationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    @Autowired
    RouteDAO dao;

    @Autowired
    RouteConverter converter;

    @Transactional
    public void save(RouteDTO routeDTO) {
        dao.save(converter.toEntity(routeDTO));
    }

    @Transactional
    public List<RouteDTO> getAll() {
        return dao.getAll().stream().map((a)->converter.toDto(a)).collect(Collectors.toList());
    }

}
