package com.scatler.rrweb.controller;

import com.scatler.rrweb.entity.Stations;
import com.scatler.rrweb.entity.User;
import com.scatler.rrweb.entity.objects.StationTimeTable;
import com.scatler.rrweb.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Controller
@RequestMapping("/station")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/timeTable")
    public String getTimeTable (@RequestParam("stationId") int id,@RequestParam("day") Date day, Model model ) {

        stationService.getStationSchedule(id,day);

        return "station-timetable";
    }


    //TODO delete (it is just a testing)
    @GetMapping("/timeTableTest")
    public String getTimeTableTest (Model model ) {

        List<StationTimeTable> tt = stationService.getStationSchedule(1,new Date(2019,8,1));
        List<Stations> stationsList = stationService.getAllStations();
        model.addAttribute("timetable", tt);
        model.addAttribute("stations",stationsList);
        return "station-timetable";
    }


}
