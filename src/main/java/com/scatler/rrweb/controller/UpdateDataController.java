package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.dto.forms.StationTimeTableWrapper;
import com.scatler.rrweb.service.impl.StationService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController("webapi")
public class UpdateDataController {
    @Autowired
    private StationService stationService;

    @RequestMapping(path = "/update/{stationId}", produces = "application/json")
    public StationTimeTableWrapper sendData(HttpServletResponse response, @PathVariable Integer stationId) throws IOException {
        Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        List<StationTimeTable> list = stationService.getStationSchedule(stationId, today);
        return new StationTimeTableWrapper(list);
    }
}
