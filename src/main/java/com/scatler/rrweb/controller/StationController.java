package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.LineDTO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.dto.forms.StationTimeTableForm;
import com.scatler.rrweb.entity.Line;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.objects.selectors.TimeTableSelector;
import com.scatler.rrweb.entity.objects.validator.StationValidator;
import com.scatler.rrweb.service.impls.IStationTimeTable;
import com.scatler.rrweb.service.impls.StationService;
import com.scatler.rrweb.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/station")
@SessionAttributes({"stations","selector"})
public class StationController {

    @Autowired
    @Qualifier("stationService")
    private IService<StationDTO, Integer> stationService;

    @Autowired
    @Qualifier("stationService")
    private IStationTimeTable timeTableService;

    @Autowired
    @Qualifier("lineService")
    private IService<LineDTO, Integer> lineService;

    @Autowired
    private StationValidator stationValidator;

    @InitBinder("station")
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.addValidators(stationValidator);
    }

    @GetMapping("/timeTable") // First page
    public String showTimeTable(Model model) {

        TimeTableSelector selector = new TimeTableSelector();
        List<StationDTO> stationsList = stationService.getAll();
        StationTimeTableForm genForm = new StationTimeTableForm();
        model.addAttribute("genForm",genForm);
        model.addAttribute("selector",selector);
        model.addAttribute("stations", stationsList);
        return "station-timetable";
    }

    @PostMapping("/getTimeTable")
    public ModelAndView getTimeTable(@ModelAttribute("selector") @Valid TimeTableSelector ts, BindingResult res, @ModelAttribute("stations") List<StationDTO> stations) {

        if (res.hasErrors()) {
            ModelAndView mv = new ModelAndView();
            mv.addObject("stations", stations);
            mv.addObject("selector", ts);
            mv.setViewName("station-timetable");
            return mv;

        } else {

            List<StationTimeTable> list = timeTableService.getStationSchedule(ts.getId(),ts.getDay());
            StationTimeTableForm genForm = new StationTimeTableForm(list);
            ModelAndView mv = new ModelAndView();
            mv.setViewName("station-timetable");
            mv.addObject("genForm",genForm);
            return  mv;
        }

    }

    @RequestMapping("/add")
    public String addStation(Model model) {
        Station station = new Station();
        List<LineDTO> lines = lineService.getAll();
        model.addAttribute(station);
        model.addAttribute("listLines", lines);
        return "station-add";
    }

    @RequestMapping("/save")
    public String saveStation(@Validated StationDTO station, BindingResult res, Model model, @ModelAttribute("listLines") ArrayList<Line> lines) {

        if (res.hasErrors()) {
            model.addAttribute(station);
            model.addAttribute(lines);
            return "station-add";
        }

        stationService.save(station);
        model.addAttribute("confirmMessage", "Station added");
        return "generic-confirm";
    }
}
