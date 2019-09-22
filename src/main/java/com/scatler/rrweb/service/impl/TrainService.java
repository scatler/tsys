package com.scatler.rrweb.service.impl;

import com.scatler.rrweb.dao.impl.TrainRouteDAO;
import com.scatler.rrweb.dao.impl.TrainDAO;
import com.scatler.rrweb.dto.TrainDTO;
import com.scatler.rrweb.dto.TrainRouteDTO;
import com.scatler.rrweb.dto.ViewAllTrain;
import com.scatler.rrweb.service.converter.TrainConverter;
import com.scatler.rrweb.service.converter.TrainRouteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainService {
    @Autowired
    private TrainDAO dao;
    @Autowired
    private TrainConverter converter;
    @Autowired
    private TrainRouteDAO trainRouteDAO;
    @Autowired
    private TrainRouteConverter trainRouteConverter;

    @Transactional
    public void save(TrainDTO trainDTO) {
        dao.save(converter.toEntity(trainDTO));
    }

    @Transactional
    public List<TrainDTO> getAll() {
        return dao.getAll().stream().map((a) -> converter.toDto(a)).collect(Collectors.toList());
    }

    @Transactional
    public void saveTRD(TrainRouteDTO dto) {
        trainRouteDAO.save(trainRouteConverter.toEntity(dto));
    }

    public TrainDTO get(Integer id) {
        return converter.toDto(dao.get(id));
    }

    @Transactional
    public Integer addOrUpdate(TrainDTO dto) {
        return dao.addOrUpdate(converter.toEntity(dto));
    }
}
