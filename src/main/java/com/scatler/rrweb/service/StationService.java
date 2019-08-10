package com.scatler.rrweb.service;

import com.scatler.rrweb.dao.StationDAO;

import com.scatler.rrweb.entity.Line;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.TrainRouteidDay;
import com.scatler.rrweb.entity.objects.searchresult.StationTimeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by alexkpc on 01.08.2019.
 */

@Service
public class StationService {

    @Autowired
    private StationDAO stationDAO;

    @Transactional
    public List<StationTimeTable> getStationSchedule(int id, Date day) {

        return stationDAO.getStationSchedule(id, day);
    }

    @Transactional
    public List<Station> getAllStations() {
        return stationDAO.getAllStations();
    }

    @Transactional
    public Station getStation(int id) {
        return stationDAO.getStation(id);
    }

    @Transactional
    public TrainRouteidDay getTRD (int id ) {return
    stationDAO.getTRD(id);
    }

    @Transactional
    public void saveStation(Station station) {
        stationDAO.saveStation(station);
    }

    @Transactional
    public List<Line> getAllLines() {
       return stationDAO.getAllLines();

    }
}
