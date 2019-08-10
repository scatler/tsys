package com.scatler.rrweb.controller;

import com.scatler.rrweb.entity.Train;
import com.scatler.rrweb.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/train")
public class TrainController {

    @Autowired
    TrainService trainService;

    @GetMapping("/add")
    public String add(Model model) {
        Train train = new Train();
        model.addAttribute(train);
        return "train-add";
    }

    @PostMapping("save")
    public String save(@Validated Train train, BindingResult res) {
        if (res.hasErrors()) {
            return "train-add";
        }
        trainService.save(train);
        return "generic-confirm";
    }
}
