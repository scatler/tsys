package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private IService<UserDTO,Integer> userService;

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
        return "add-user";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.removeById(id);
        return "redirect:list";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("userId") int id, Model model) {
        UserDTO user = userService.get(id);
        model.addAttribute("user", user);
        return "add-user";
    }
}
