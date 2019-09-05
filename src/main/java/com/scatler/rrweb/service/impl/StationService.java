package com.scatler.rrweb.service.impl;

import com.scatler.rrweb.dao.impl.StationDAO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.service.converter.StationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationService  {

    @Autowired
    StationDAO dao;

    @Autowired
    StationConverter converter;

    @Transactional
    public List<StationTimeTable> getStationSchedule(Integer station_id, Date day) {
        return dao.getStationSchedule(station_id,day);
    }

    @Transactional
    public List<StationDTO> getAll() {
        return dao.getAll().stream().map((a)->converter.toDto(a)).collect(Collectors.toList());
    }

    @Transactional
    public void save(StationDTO station) {
        dao.save(converter.toEntity(station));
    }


    public StationDTO get(Integer id) {
            return converter.toDto(dao.get(id));
    }

    @Transactional
    public Integer addOrUpdate(StationDTO dto) {
        return dao.addOrUpdate(converter.toEntity(dto));
    }
}
