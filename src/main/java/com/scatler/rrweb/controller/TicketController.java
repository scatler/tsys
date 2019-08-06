package com.scatler.rrweb.controller;

import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.objects.searchresult.AvailableTrain;
import com.scatler.rrweb.service.StationService;
import com.scatler.rrweb.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private StationService stationService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("")
    public String findTrains (Model model) {
        List<Station> stationsList = stationService.getAllStations();
        model.addAttribute("stations",stationsList);
        ticketService.getAvailableTrains(1001, 1013, new Date(2019,1,1));
        return "findtrains";
    }


}
