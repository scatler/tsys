package com.scatler.rrweb.service.impl;

import com.scatler.rrweb.dao.impl.RouteStationDAO;
import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.service.SenderServiceMQ;
import com.scatler.rrweb.service.converter.RouteStationConverter;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public List<RouteStationDTO> getAll() {
        return dao.getAll().stream().map((a)->converter.toDto(a)).collect(Collectors.toList());
    }

    @Transactional
    public void update(RouteStationDTO dto) {
        dao.update(converter.toEntity(dto));
    }
}
