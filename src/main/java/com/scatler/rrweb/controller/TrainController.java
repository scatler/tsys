package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.dto.TrainDTO;
import com.scatler.rrweb.dto.TrainRouteDTO;
import com.scatler.rrweb.dto.ViewAllTrain;
import com.scatler.rrweb.dto.forms.RouteStationForm;
import com.scatler.rrweb.dto.forms.ViewAllTrainsForm;
import com.scatler.rrweb.entity.objects.validator.AddRouteStationFormValidator;
import com.scatler.rrweb.service.impl.RouteService;
import com.scatler.rrweb.service.impl.RouteStationService;
import com.scatler.rrweb.service.impl.StationService;
import com.scatler.rrweb.service.impl.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@SessionAttributes({"routesDtos", "stationDtos", "routeStationForm"})
public class TrainController {
    @Autowired
    private TrainService trainService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private RouteStationService routeStationService;
    @Autowired
    private StationService stationService;
    @Autowired
    private AddRouteStationFormValidator routeStationFormValidator;

    @InitBinder("routeStationForm")
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.addValidators(routeStationFormValidator);
    }

    @InitBinder("trd")
    public void initBinderTRD(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
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
    public ModelAndView save(@Validated TrainDTO trainDTO, BindingResult res) {
        ModelAndView mv = new ModelAndView("train-add");
        if (res.hasErrors()) {
            mv.addObject(trainDTO);
            return mv;
        }
        trainService.save(trainDTO);
        mv.addObject("success", "Train has been added");
        return mv;
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
    public ModelAndView addRow(@Validated RouteStationForm routeStationForm, BindingResult res, String submit) {
        ModelAndView mv = new ModelAndView("route-station-add");
        switch (submit) {
            case "addRow":
                routeStationForm.getRs().add(new RouteStationDTO());
                return mv;
            case "go":
                if (!res.hasErrors()) {
                    routeStationForm.prepareData();
                    routeStationService.saveAll(routeStationForm.getRs());
                    mv.addObject("success", "New route has been submitted");
                    return mv;
                } else {
                    mv.addObject("hasErrors", true);
                    mv.addObject(routeStationForm);
                    return mv;
                }
            case "Delete rows":
                routeStationForm.prepareData();
                return mv;
            case "Get data...":
                if (res.getFieldError("routeId") == null) {
                    Integer routeId = routeStationForm.getRouteId();
                    ArrayList<RouteStationDTO> rsDTO = new ArrayList<>(routeStationService.getByRouteId(routeId));
                    RouteStationForm routeStationFormNew = new RouteStationForm();
                    routeStationFormNew.setRouteId(routeStationForm.getRouteId());
                    routeStationFormNew.setRs(rsDTO);
                    mv.addObject("routeStationForm", routeStationFormNew);
                    return mv;
                } else {
                    mv.addObject("hasErrors", true);
                    return mv;
                }
        }
        mv.addObject("error", "Something went wrong");
        return mv;
    }

    @GetMapping("/viewAllTrains")
    public ModelAndView viewAllTrains() {
        ModelAndView mv = new ModelAndView();
        List<ViewAllTrain> list = trainService.viewAllTrains();
        ViewAllTrainsForm genForm = new ViewAllTrainsForm(list);
        mv.addObject("genForm", genForm);
        mv.setViewName("trains-view-all");
        return mv;
    }

    @GetMapping("/TRD")
    public ModelAndView assignTrainToRoute() {
        ModelAndView mv = new ModelAndView();
        TrainRouteDTO dto = new TrainRouteDTO();
        List<RouteDTO> routes = routeService.getAll();
        List<TrainDTO> trains = trainService.getAll();
        mv.setViewName("train-trd");
        mv.addObject("trd", dto);
        mv.addObject("trains", trains);
        mv.addObject("routes", routes);
        return mv;
    }

    @PostMapping("/saveTRD")
    public ModelAndView saveAssginTrainToRoute(@ModelAttribute("trd") @Validated TrainRouteDTO dto, BindingResult res) {
        if (res.hasErrors()) {
            ModelAndView mv = new ModelAndView();
            mv.addObject("trd", dto);
            mv.setViewName("train-trd");
            return mv;
        } else {
            trainService.saveTRD(dto);
            return new ModelAndView("train-trd", "success", "train assigned");
        }
    }
}
