package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.LineDTO;
import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.service.impl.LineService;
import com.scatler.rrweb.service.impl.RouteStationService;
import com.scatler.rrweb.service.impl.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController("routeApi")
public class RouteRestController {
    @Autowired
    private RouteStationService routeStationService;
    @Autowired
    private StationService stationService;
    @Autowired
    private LineService lineService;

    @RequestMapping(path = "/routes", produces = "application/json")
    public List<RouteStationDTO> sendData(HttpServletResponse response) throws IOException {
        return routeStationService.getAll();
    }

    @RequestMapping(value = "/saveRoute", //
            method = RequestMethod.PUT, //
            consumes = "application/json")
    public RouteStationDTO updateRoute(@RequestBody RouteStationDTO dto) {
        System.out.println("(Service Side) Editing employee with Id: " + dto.getId());
        routeStationService.update(dto);
        return new RouteStationDTO();
    }

    @RequestMapping(value = "/saveStation", //
            method = RequestMethod.PUT, //
            consumes = "application/json")
    public RouteStationDTO updateStation(@RequestBody StationDTO dto) {
        System.out.println("(Service Side) Editing employee with Id: " + dto.getId());
        stationService.save(dto);
        return new RouteStationDTO();
    }

    @RequestMapping(path = "/stations", produces = "application/json")
    public List<StationDTO> getAllStations(HttpServletResponse response) throws IOException {
        return stationService.getAll();
    }


    @RequestMapping(path = "/lines", produces = "application/json")
    public List<LineDTO> getAllLines(HttpServletResponse response) throws IOException {
        return lineService.getAll();
    }



}
