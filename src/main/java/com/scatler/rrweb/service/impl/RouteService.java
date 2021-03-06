package com.scatler.rrweb.service.impl;

import com.scatler.rrweb.dao.impl.RouteDAO;
import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.service.converter.RouteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    @Autowired
    private RouteDAO dao;

    @Autowired
    private RouteConverter converter;

    @Transactional
    public void save(RouteDTO routeDTO) {
        dao.save(converter.toEntity(routeDTO));
    }

    @Transactional
    public List<RouteDTO> getAll() {
        return dao.getAll().stream().map((a)->converter.toDto(a)).collect(Collectors.toList());
    }

    @Transactional
    public Integer addOrUpdate(RouteDTO dto) {
        return dao.addOrUpdate(converter.toEntity(dto));
    }

    public RouteDTO get(Integer id) {
        return converter.toDto(dao.get(id));
    }
}
