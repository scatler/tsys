package com.scatler.rrweb.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scatler.rrweb.dto.AllPassengersDTO;
import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.dto.forms.AvailableTrain;
import com.scatler.rrweb.entity.objects.exception.FoundSamePassengerException;
import com.scatler.rrweb.entity.objects.exception.NotEnoughTimeBeforeDeparture;
import com.scatler.rrweb.service.impl.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController("ticket-api")
public class TicketRestController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping(path = "/availableTrains", produces = "application/json")
    public List<AvailableTrain> getAvailableTrains(@RequestParam Integer stationFrom,
                                                   @RequestParam Integer stationTo,
                                                   @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") Date dayFrom,
                                                   @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") Date dayTo
    ) {
        return ticketService.getAvailableTrains(stationFrom, stationTo, dayFrom, dayTo);
    }

    @RequestMapping(path = "/saveTicket", consumes = "application/json", produces = "application/json")
    public Integer registerTicket(@RequestBody TicketDTO dto) throws FoundSamePassengerException, NotEnoughTimeBeforeDeparture {
        return ticketService.registerTicket(dto);
    }

    @RequestMapping(path = "/infoPass", produces = "application/json")
    public List<AllPassengersDTO> loadAllPass(@RequestParam Integer routeId,
                                              @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") Date dayFrom) {
        return ticketService.getAllPassengers(routeId, dayFrom);
    }
}
