package com.scatler.rrweb.controller;

import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.entity.objects.searchresult.AvailableTrain;
import com.scatler.rrweb.entity.objects.selectors.AvailableTrainSelector;
import com.scatler.rrweb.service.RouteService;
import com.scatler.rrweb.service.StationService;
import com.scatler.rrweb.service.TicketService;
import com.scatler.rrweb.util.crudformabstract.forms.AvailableTrainForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tickets")
@SessionAttributes(types = Ticket.class)
public class TicketController {

    @Autowired
    private StationService stationService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private RouteService routeService;

    @GetMapping("start")
    public String findTrains(Model model) {
        List<Station> stationsList = stationService.getAllStations();
        model.addAttribute("stations", stationsList);
        return "tickets-find-trains";
    }

    @PostMapping("/trains")
    public ModelAndView getAvailableTrainTable(@ModelAttribute @Valid AvailableTrainSelector ts, BindingResult res) {

        ModelAndView mv;
        AvailableTrainForm form = null;
        if (res.hasErrors()) {

            Map<String, String> errors = res.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
        }
        else {
            List<AvailableTrain> trains = ticketService.getAvailableTrains(1001, 1013, new Date(2019, 1, 1));

            form = new AvailableTrainForm(trains);
        }

        mv = new ModelAndView("available-trains-table","genForm",form);
        return mv;
    }

    @GetMapping("/buy")
    public String buyTicket(@RequestParam("id") Integer trid,
                            @RequestParam("stationFrom") Integer stationFrom,
                            @RequestParam("stationTo") Integer stationTo,
                            Model model) {

        Ticket ticket = new Ticket();
        //ticket.setId(1);
        ticket.setStation1Id(stationService.getStation(stationFrom));
        ticket.setStation2Id(stationService.getStation(stationTo));
        ticket.setTrd(routeService.getTrainRouteidDay(trid));
        model.addAttribute("ticket",ticket);
        return "ticket-buy";
    }

    @PostMapping("/saveBuy")
    public String saveBuy( Ticket ticket) {
        ticketService.saveTicket (ticket);
        return "redirect:start";
    }
}
