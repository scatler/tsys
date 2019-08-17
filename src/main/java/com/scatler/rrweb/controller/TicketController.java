package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.AllPassengersDTO;
import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.dto.forms.AvailableTrain;
import com.scatler.rrweb.dto.forms.AvailableTrainForm;
import com.scatler.rrweb.dto.forms.ViewAllPassengersForm;
import com.scatler.rrweb.entity.objects.exception.FoundSamePassengerException;
import com.scatler.rrweb.entity.objects.exception.NotEnoughTimeBeforeDeparture;
import com.scatler.rrweb.entity.objects.selectors.AvailableTrainSelector;
import com.scatler.rrweb.entity.objects.selectors.ViewAllPassengersSelector;
import com.scatler.rrweb.service.impls.RouteService;
import com.scatler.rrweb.service.impls.StationService;
import com.scatler.rrweb.service.impls.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
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
@SessionAttributes({"trainSelector", "stations", "ticket","selector", "list"})
public class TicketController {

    @Autowired
    private StationService stationService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private RouteService routeService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping("/start")
    public String findTrains(Model model) {
        List<StationDTO> stations = stationService.getAll();
        AvailableTrainSelector trainSelector = new AvailableTrainSelector();
        AvailableTrainForm genForm = new AvailableTrainForm();
        model.addAttribute("genForm", genForm);
        model.addAttribute("trainSelector", trainSelector);
        model.addAttribute("stations", stations);
        return "train-find-available";
    }

    @PostMapping("/trains")
    public ModelAndView getAvailableTrainTable(@ModelAttribute("trainSelector") @Valid AvailableTrainSelector ts,
                                               BindingResult res,
                                               @ModelAttribute("stations") List<StationDTO> stations
                                               ){
        if (res.hasErrors()) {
            ModelAndView mv = new ModelAndView();
            mv.addObject("stations", stations);
            mv.addObject("trainSelector", ts);
            mv.setViewName("train-find-available");
            return mv;
        } else {
            List<AvailableTrain> trains = ticketService.getAvailableTrains(ts.getStationFrom(), ts.getStationTo(), ts.getDay());
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
    public String saveBuy(@ModelAttribute("ticket") @Validated TicketDTO ticket, BindingResult res, Model model) throws FoundSamePassengerException, NotEnoughTimeBeforeDeparture {
        if (res.hasErrors()) {
            return "ticket-buy-valid";
        }
        ticketService.registerTicket(ticket);
        return "ticket-buy-confirm";
    }

    @GetMapping("/viewAllPassengers") // View all passengers start page
    public ModelAndView viewAllPassengers () {
        List<RouteDTO> routeDtos = routeService.getAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ticket-viewallpassengers");
        mv.addObject("list",routeDtos);
        mv.addObject("selector", new ViewAllPassengersSelector());
        return mv;
    }

    @PostMapping("/loadAllPassengers")
    public ModelAndView loadAllPassengers (@ModelAttribute("selector") @Validated ViewAllPassengersSelector vs,
                                           BindingResult res,
                                           @ModelAttribute("list") List<RouteDTO> routeDtos) {

        ModelAndView mv = new ModelAndView();
        if (res.hasErrors()) {
            mv.addObject("selector",vs);
            mv.addObject("list",routeDtos);
            mv.setViewName("ticket-viewallpassengers");
            return mv;
        } else  {
            List<AllPassengersDTO> passengers = ticketService.getAllPassengers(vs.getId(),vs.getDay());
            ViewAllPassengersForm genForm = new ViewAllPassengersForm(passengers);
            mv.addObject("genForm",genForm);
            mv.setViewName("ticket-viewallpassengers");
        }
        return mv;
    }


}
