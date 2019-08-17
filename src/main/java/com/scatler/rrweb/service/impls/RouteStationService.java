package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.RouteStationDAO;
import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.entity.RouteStation;
import com.scatler.rrweb.service.converter.IConverter;
import com.scatler.rrweb.service.converter.RouteStationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteStationService {
    @Autowired
    RouteStationDAO dao;
    @Autowired
    RouteStationConverter converter;

    @Transactional
    public void saveAll(ArrayList<RouteStationDTO> rs) {
        dao.saveAll(rs.stream().map((a) -> converter.toEntity(a)).collect(Collectors.toList()));
    }

    @Transactional
    public List<RouteStationDTO> getByRouteId(Integer routeId) {
        return dao.getByRouteId(routeId).stream().map((a)->converter.toDto(a)).collect(Collectors.toList());
    }
}
