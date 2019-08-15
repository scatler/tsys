package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.dto.forms.AvailableTrain;
import com.scatler.rrweb.dto.forms.AvailableTrainForm;
import com.scatler.rrweb.entity.objects.exception.FoundSamePassengerException;
import com.scatler.rrweb.entity.objects.selectors.AvailableTrainSelector;
import com.scatler.rrweb.service.interfaces.ICustomTicketService;
import com.scatler.rrweb.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tickets")
@SessionAttributes({"trainSelector","stations","ticket"})
public class TicketController {

    @Autowired
    @Qualifier("stationService")
    private IService<StationDTO, Integer> stationService;
    @Autowired
    @Qualifier("ticketService")
    private IService<TicketDTO, Integer> ticketService;
    @Autowired
    @Qualifier("ticketService")
    private ICustomTicketService customTicketService;
    @Autowired
    @Qualifier("routeService")
    private IService<RouteDTO, Integer> service;

    @GetMapping("start")
    public String findTrains(Model model) {


        List<StationDTO> stations  = stationService.getAll();
        AvailableTrainSelector trainSelector = new AvailableTrainSelector();
        AvailableTrainForm genForm = new AvailableTrainForm();
        model.addAttribute("genForm", genForm);
        model.addAttribute("trainSelector", trainSelector);
        model.addAttribute("stations", stations);
        return "train-find-available";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @PostMapping("/trains")
    public ModelAndView getAvailableTrainTable(@ModelAttribute("trainSelector") @Valid AvailableTrainSelector ts, BindingResult res, Model model, @ModelAttribute("stations") List<StationDTO> stations) {


        if (res.hasErrors()) {
            ModelAndView mv = new ModelAndView();
            mv.addObject("stations", stations);
            mv.addObject("trainSelector", ts);
            mv.setViewName("train-find-available");
            return mv;
        } else {

            List<AvailableTrain> trains = (customTicketService
                    .getAvailableTrains
                            (ts.getStationFrom(), ts.getStationTo(), ts.getDay()));

            AvailableTrainForm form = new AvailableTrainForm(trains);
            return new ModelAndView("train-find-available", "genForm", form);
        }

    }

    @GetMapping("/buy")
    public String buyTicket(@RequestParam("id") Integer trid,
                            @RequestParam("stationFrom") Integer stationFrom,
                            @RequestParam("stationTo") Integer stationTo,
                            Model model) {

        TicketDTO ticket = new TicketDTO();
        ticket.setTrd(trid);
        ticket.setStation1Id(stationFrom);
        ticket.setStation2Id(stationTo);
        model.addAttribute("ticket", ticket);
        return "ticket-buy-valid";
    }


    @PostMapping("/saveBuy")
    public String saveBuy(@ModelAttribute("ticket") @Validated TicketDTO ticket, BindingResult res, Model model) throws FoundSamePassengerException {
        if (res.hasErrors()) {
            return "ticket-buy-valid";
        }
        customTicketService.registerTicket(ticket);
        return "ticket-buy-confirm";
    }

    @ExceptionHandler(FoundSamePassengerException.class)
    public ModelAndView handleUsernameNotFoundException(HttpServletRequest request, Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ticket-buy-valid");
        mv.addObject("error",ex.toString());
        mv.addObject("ticket",new TicketDTO());
        return mv;
    }
}
