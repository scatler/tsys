package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.LineDTO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.entity.Line;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.objects.selectors.TimeTableSelector;
import com.scatler.rrweb.entity.objects.validator.StationValidator;
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
@SessionAttributes(types = java.util.ArrayList.class)
public class StationController {

    @Autowired
    @Qualifier("stationService")
    private IService<StationDTO,Integer> stationService;

    @Autowired
    @Qualifier("lineService")
    private IService<LineDTO,Integer> lineService;

    @Autowired
    private StationValidator stationValidator;

    @InitBinder("station")
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.addValidators(stationValidator);
    }

    //TODO remove AJAX
    @PostMapping("/getTimeTable")
    public ModelAndView getTimeTable(@ModelAttribute @Valid TimeTableSelector ts, BindingResult res) {

        int station_id = Integer.parseInt(ts.getId());
        Date day = new Date(ts.getDay()); //
        if (res.hasErrors()) {

            //Get error message
            Map<String, String> errors = res.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

  /*          response.setValidated(false);
            respone.setErrorMessages(errors);*/
        } else {

            //TODO handle errors
            System.out.printf("Errors");

        }

        List<StationTimeTable> timetable = ((StationService)stationService).getStationSchedule(station_id, day);
        ModelAndView mv = new ModelAndView("gettimetable", "stations", timetable);
        return mv;
    }

    //TODO rename
    @GetMapping("/timeTable")
    public String showTimeTable(Model model) {
        List<StationDTO> stationsList = stationService.getAll();
        model.addAttribute("stations", stationsList);
        return "station-timetable";
    }

    @RequestMapping("/add")
    public String addStation (Model model) {
        Station station = new Station();
        List<LineDTO> lines = lineService.getAll();
        model.addAttribute(station);
        model.addAttribute("listLines",lines);
        return "station-add";
    }

    @RequestMapping("/save")
    public String  saveStation (@Validated StationDTO station, BindingResult res,Model model, @ModelAttribute("listLines") ArrayList<Line> lines) {

        if (res.hasErrors()){
            model.addAttribute(station);
            model.addAttribute(lines);
            return "station-add";
        }

        stationService.save(station);
        model.addAttribute("confirmMessage","Station added");
        return "generic-confirm";
    }
}
