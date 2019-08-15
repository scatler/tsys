package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dto.forms.StationTimeTable;

import java.util.Date;
import java.util.List;

public interface IStationTimeTable {

    List<StationTimeTable> getStationSchedule(Integer id, Date day);
}
