package com.scatler.rrweb.service.impl;

import com.scatler.rrweb.dao.impl.TrainRouteDAO;
import com.scatler.rrweb.dto.TrainDTO;
import com.scatler.rrweb.dto.TrainRouteDTO;
import com.scatler.rrweb.entity.TrainRouteidDay;
import com.scatler.rrweb.service.converter.TrainRouteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrdService {
    @Autowired
    private TrainRouteDAO dao;
    @Autowired
    private TrainRouteConverter converter;
    @Transactional
    public List<TrainRouteDTO> getAll() {
        List<TrainRouteDTO> list = new ArrayList<>();
        for (TrainRouteidDay a : dao.getAll()) {
            TrainRouteDTO trainRouteDTO = converter.toDto(a);
            list.add(trainRouteDTO);
        }
        return list;
    }

    @Transactional
    public TrainRouteDTO get(Integer id) {
        return converter.toDto(dao.get(id));
    }
    @Transactional
    public Integer addOrUpdate(TrainRouteDTO dto) {
        return dao.addOrUpdate(converter.toEntity(dto));
    }
}
