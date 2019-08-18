package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.service.impl.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/routes")
public class RouteController {
    @Autowired
    private RouteService service;

    @GetMapping("/add")
    public String add(Model model) {
        RouteDTO routeDTO = new RouteDTO();
        model.addAttribute(routeDTO);
        return "route-add";
    }

    @PostMapping("save")
    public ModelAndView save(@Validated RouteDTO routeDTO, BindingResult res) {
        ModelAndView mv = new ModelAndView("route-add");
        if (res.hasErrors()) {
            mv.addObject("hasErrors", true);
            mv.addObject(routeDTO);
            return mv;
        }
        service.save(routeDTO);
        mv.addObject("success", "Route has been added");
        return mv;
    }
}
