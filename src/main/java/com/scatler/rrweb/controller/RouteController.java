package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.service.impls.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String save(@Validated RouteDTO routeDTO, BindingResult res) {
        if (res.hasErrors()) {
            return "route-add";
        }
        service.save(routeDTO);
        return "generic-confirm";
    }

}
