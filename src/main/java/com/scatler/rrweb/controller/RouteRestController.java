package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.dto.StationDTO;
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

    @RequestMapping(path = "/routes", produces = "application/json")
    public List<RouteStationDTO> sendData(HttpServletResponse response) throws IOException {
        return routeStationService.getAll();
    }

    @RequestMapping(value = "/saveRoute", //
            method = RequestMethod.PUT, //
            consumes = "application/json")
    public RouteStationDTO update(@RequestBody RouteStationDTO dto) {
        System.out.println("(Service Side) Editing employee with Id: " + dto.getId());
        routeStationService.update(dto);
        return new RouteStationDTO();
    }

    @RequestMapping(path = "/stations", produces = "application/json")
    public List<StationDTO> getAllStations(HttpServletResponse response) throws IOException {
        return stationService.getAll();
    }



}
