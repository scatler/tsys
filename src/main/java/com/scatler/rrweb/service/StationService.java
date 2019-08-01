package com.scatler.rrweb.service;

import com.scatler.rrweb.dao.StationDAO;
import com.scatler.rrweb.entity.StationSchedule;

/**
 * Created by alexkpc on 01.08.2019.
 */
public class StationService {

    private StationDAO stationDAO;

    public StationSchedule getStationSchedule (String day) {


        return stationDAO.getStationSchedule(day);
    }


}
