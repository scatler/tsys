package com.scatler.rrweb.controller;

import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.entity.TrainRouteidDay;
import com.scatler.rrweb.entity.objects.TestSessionTransferObject;
import com.scatler.rrweb.entity.objects.searchresult.AvailableTrain;
import com.scatler.rrweb.service.RouteService;
import com.scatler.rrweb.service.StationService;
import com.scatler.rrweb.service.TicketService;
import com.scatler.rrweb.util.crudformabstract.forms.AvailableTrainForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tickets")
@SessionAttributes(types = TestSessionTransferObject.class)
public class TicketController {

    @Autowired
    private StationService stationService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private RouteService routeService;

    @GetMapping("")
    public String findTrains(Model model) {
        List<Station> stationsList = stationService.getAllStations();
        model.addAttribute("stations", stationsList);
        return "find-trains";
    }

    @GetMapping("/trains")
    public String getAvailableTrainTable(Model model) {
        List<AvailableTrain> trains = ticketService.getAvailableTrains(1001, 1013, new Date(2019, 1, 1));
        AvailableTrainForm form = new AvailableTrainForm(trains);
        model.addAttribute("genForm",form);
        return "available-trains-table";
    }

    @GetMapping("/buy")
    public String buyTicket(@RequestParam("id") int rowInResultSet,
                            AvailableTrain trainForm,
                            Model model) {

        TestSessionTransferObject to = new TestSessionTransferObject(rowInResultSet);
        Ticket t = new Ticket();
        Assert.notNull(trainForm);
        model.addAttribute(to);
        model.addAttribute("ticket", t);
        return "ticket-buy";

    }

    @PostMapping("/saveBuy")
    public String saveBuy( TestSessionTransferObject to) {
        Ticket ticket = new Ticket();
        ticket.setId(222);
        //ticket.setStation1Id(stationService.getStation(1001));
        //ticket.setStation2Id(stationService.getStation(1013));
        routeService.getTrainRouteidDay(55);
        //stationService.getTRD(3);
        ticketService.saveTicket (ticket);
        return "redirect:trains";
    }

}
