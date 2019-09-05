package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.LineDTO;
import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.service.impl.LineService;
import com.scatler.rrweb.service.impl.RouteService;
import com.scatler.rrweb.service.impl.RouteStationService;
import com.scatler.rrweb.service.impl.StationService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private RouteService routeService;
    @Autowired
    private Logger log;

    @RequestMapping(value = "/saveStation", //
            method = RequestMethod.PUT, //
            consumes = "application/json")
    public Integer updateStation(@RequestBody StationDTO dto) {
        log.debug("Add or update station with Id: " + dto.getId());
        return stationService.addOrUpdate(dto);
    }

    @RequestMapping(value = "/saveRoute", //
            method = RequestMethod.PUT, //
            consumes = "application/json")
    public Integer updateRoute(@RequestBody RouteDTO dto) {
        log.debug("Add or update station with Id: " + dto.getId());
        return routeService.addOrUpdate(dto);
    }

    @RequestMapping(value = "/saveRouteStation", //
            method = RequestMethod.PUT, //
            consumes = "application/json")
    public Integer updateRouteStation(@RequestBody RouteStationDTO dto) {
        log.debug("Add or update modified route" + dto.getId());
        return routeStationService.addOrUpdate(dto);
    }

    @RequestMapping(path = "/stations", produces = "application/json")
    public List<StationDTO> getAllStations(HttpServletResponse response) throws IOException {
        return stationService.getAll();
    }

    @RequestMapping(path = "/stations/{id}", produces = "application/json")
    public StationDTO getStationByID(@PathVariable Integer id) throws IOException {
        return stationService.get(id);
    }

    @RequestMapping(path = "/lines", produces = "application/json")
    public List<LineDTO> getAllLines(HttpServletResponse response) throws IOException {
        return lineService.getAll();
    }

    @RequestMapping(path = "/routes", produces = "application/json")
    public List<RouteDTO> getData() throws IOException {
        return routeService.getAll();
    }

    @RequestMapping(path = "/routes/{id}", produces = "application/json")
    public RouteDTO getRouteById(@PathVariable Integer id) throws IOException {
        return routeService.get(id);
    }

    @RequestMapping(path = "/routesStations", produces = "application/json")
    public List<RouteStationDTO> sendData(HttpServletResponse response) throws IOException {
        return routeStationService.getAll();
    }
}
