package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.dto.TrainDTO;
import com.scatler.rrweb.dto.ViewAllTrain;
import com.scatler.rrweb.dto.forms.RouteStationForm;
import com.scatler.rrweb.dto.forms.ViewAllTrainsForm;
import com.scatler.rrweb.service.impls.RouteService;
import com.scatler.rrweb.service.impls.RouteStationService;
import com.scatler.rrweb.service.impls.StationService;
import com.scatler.rrweb.service.impls.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/train")
@SessionAttributes(types = java.util.ArrayList.class)
public class TrainController {

    @Autowired
    private TrainService trainService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private RouteStationService routeStationService;
    @Autowired
    private StationService stationService;

    @InitBinder("routeStationForm")
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping("/add")
    public String add(Model model) {
        TrainDTO trainDTO = new TrainDTO();
        model.addAttribute(trainDTO);
        return "train-add";
    }

    @PostMapping("save")
    public String save(@Validated TrainDTO trainDTO, BindingResult res) {
        if (res.hasErrors()) {
            return "train-add";
        }
        trainService.save(trainDTO);
        return "generic-confirm";
    }

    @GetMapping("/addRouteStation")
    public String assignRouteToStation(Model model) {
        model.addAttribute("routesDtos", routeService.getAll());
        model.addAttribute("stationDtos", stationService.getAll());
        //--------------------------Initializing route----------------------
        ArrayList<RouteStationDTO> rsDTO = new ArrayList<>();
        rsDTO.add(new RouteStationDTO());
        RouteStationForm routeStationForm = new RouteStationForm();
        routeStationForm.setRs(rsDTO);
        model.addAttribute(routeStationForm);
        return "route-station-add";
    }

    @RequestMapping(value = "newRouteSubmisson", method = RequestMethod.POST)
    public ModelAndView addRow(RouteStationForm routeStationForm, String submit) {
        ModelAndView mv = new ModelAndView("route-station-add");
        if (submit.equals("addRow")) {
            routeStationForm.getRs().add(new RouteStationDTO());
            return mv;
        } else if (submit.equals("go")) {
            routeStationForm.prepareData();
            routeStationService.saveAll(routeStationForm.getRs());
        }
        return mv;
    }

    @GetMapping("/viewAllTrains")
    public ModelAndView viewAllTrains (){
        ModelAndView mv = new ModelAndView();
        List<ViewAllTrain> list = trainService.viewAllTrains();
        ViewAllTrainsForm genForm = new ViewAllTrainsForm(list);
        mv.addObject("genForm",genForm);
        mv.setViewName("trains-view-all");
        return mv;
    }
}
