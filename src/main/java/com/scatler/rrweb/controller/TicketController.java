package com.scatler.rrweb.controller;

import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.entity.objects.searchresult.AvailableTrain;
import com.scatler.rrweb.service.StationService;
import com.scatler.rrweb.service.TicketService;
import com.scatler.rrweb.util.crudformabstract.forms.AvailableTrainForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String findTrains(Model model) {
        List<Station> stationsList = stationService.getAllStations();
        model.addAttribute("stations", stationsList);
        return "find-trains";
    }

    @GetMapping("/trains")
    public String getAvailableTrainTable(Model model) {
        List<AvailableTrain> trains = ticketService.getAvailableTrains(1001, 1013, new Date(2019, 1, 1));

        model.addAttribute("genForm", new AvailableTrainForm(trains));

        return "available-trains-table";
    }

    @GetMapping("/buy")
    public String buyTicket(@RequestParam("id") int trid,
                            @RequestParam("stationFrom") String stationFrom,
                            @RequestParam("stationTo") String stationTo,
                            Model model) {

        Ticket t = new Ticket();
        model.addAttribute("ticket", t);
        model.addAttribute("trainRouteDayId",trid);
        return "ticket-buy";

    }

    @PostMapping("/saveBuy")
    public String saveBuy(@ModelAttribute("ticket") Ticket ticket) {

        System.out.println(ticket);
        return "redirect:trains";

    }
}
