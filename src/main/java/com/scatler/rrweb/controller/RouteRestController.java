package com.scatler.rrweb.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scatler.rrweb.dto.LineDTO;
import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.dto.TrainDTO;
import com.scatler.rrweb.dto.TrainRouteDTO;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.service.impl.LineService;
import com.scatler.rrweb.service.impl.RouteService;
import com.scatler.rrweb.service.impl.RouteStationService;
import com.scatler.rrweb.service.impl.StationService;
import com.scatler.rrweb.service.impl.TrainService;
import com.scatler.rrweb.service.impl.TrdService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
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
    private TrainService trainService;
    @Autowired
    private TrdService trdService;
    @Autowired
    private Logger log;

    //------------------Saving
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

    @RequestMapping(value = "/saveTrain", //
            method = RequestMethod.PUT, //
            consumes = "application/json")
    public Integer updateTrain(@RequestBody TrainDTO dto) {
        log.debug("Add or update modified train" + dto.getId());
        return trainService.addOrUpdate(dto);
    }

    @RequestMapping(value = "/saveTrd", //
            method = RequestMethod.PUT, //
            consumes = "application/json")
    public Integer updateTrd(@RequestBody TrainRouteDTO dto) {
        log.debug("Add or update modified trd" + dto.getTrainId() + "->" +
                "->" + dto.getDay() +
                "->" + dto.getRouteId());
        return trdService.addOrUpdate(dto);
    }
    //-----------------------Get service

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

    //-----------------------Trains-----------------------------
    @RequestMapping(path = "/trains", produces = "application/json")
    public List<TrainDTO> getAllTrains() throws IOException {
        return trainService.getAll();
    }

    @RequestMapping(path = "/trains/{id}", produces = "application/json")
    public TrainDTO getTrainById(@PathVariable Integer id) throws IOException {
        return trainService.get(id);
    }

    //-------------------------TRD-------------------------------
    @RequestMapping(path = "/trd", produces = "application/json")
    public List<TrainRouteDTO> getAllTrd() throws IOException {
        return trdService.getAll();
    }

    @RequestMapping(path = "/trd/{id}", produces = "application/json")
    public TrainRouteDTO getTrdById(@PathVariable Integer id) throws IOException {
        return trdService.get(id);
    }
    //------------------------Info Station --------------------------

    @RequestMapping(path = "/infoStation", produces = "application/json")
    public List<StationTimeTable> getInfoStation(@RequestParam Integer stationFrom,
                                                 @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy") Date dayFrom
                                              ) throws IOException {
        return stationService.getStationSchedule(stationFrom,dayFrom);
    }
}
