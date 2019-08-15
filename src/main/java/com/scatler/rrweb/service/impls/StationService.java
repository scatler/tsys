package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.StationDAO;
import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.service.converter.IConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Qualifier("stationService")
public class StationService extends AbstractService<Station, StationDTO> implements IStationTimeTable {

    //@Autowired
    public StationService(IDao<Station, Integer> dao, IConverter<Station, StationDTO> converter) {
        super(dao, converter);
    }

    @Override
    IDao<Station, Integer> getDao() {
        return dao;
    }

    @Override
    IConverter<Station, StationDTO> getConverter() {
        return converter;
    }

    @Transactional
    public List<StationTimeTable> getStationSchedule(Integer station_id, Date day) {
        return ((StationDAO)dao).getStationSchedule(station_id,day);
    }

}
