package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.AuDTO;
import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.entity.objects.exception.EmailExistsException;
import com.scatler.rrweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<UserDTO> users = userService.getAll();
        model.addAttribute("users", users);
        return "list-users";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "user-add";
    }

    @PostMapping("/save")
    public ModelAndView saveUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult res) throws EmailExistsException {
        ModelAndView mv = new ModelAndView("user-add");
        if (!res.hasErrors()) {
            userService.save(userDTO);
            List<UserDTO> users = userService.getAll();
            ModelAndView list = new ModelAndView("list-users", "success", "User updated");
            list.addObject("users", users);
            return list;
        } else {
            mv.addObject(userDTO);
            return mv;
        }
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.removeById(id);
        return "redirect:list";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("userId") int id, Model model) {
        List<AuDTO> aulist = userService.getAllAu();
        UserDTO user = userService.get(id);
        model.addAttribute("user", user);
        return "user-add";
    }
}
