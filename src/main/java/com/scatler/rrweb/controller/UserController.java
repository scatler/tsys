package com.scatler.rrweb.controller;

import com.scatler.rrweb.entity.User;
import com.scatler.rrweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alexkpc on 27.07.2019.
 */

@Controller
@RequestMapping ("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "list-users";
    }

    @GetMapping("/addUser")
    public String addUser (Model model) {
        User user = new User();
        model.addAttribute("user", user);
          return "add-user";
    }

    @PostMapping("/saveUser")
    public String saveUser (@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:list";
    }

    @GetMapping("/deleteUser")
    public String deleteUser (@RequestParam("userId") int id) {
        userService.deleteUser(id);
        return "redirect:list";
    }

    @GetMapping("/updateUser")
    public String updateUser (@RequestParam("userId") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "add-user";
    }
}
