package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.StationDAO;
import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.service.converter.IConverter;
import com.scatler.rrweb.service.converter.StationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
}
