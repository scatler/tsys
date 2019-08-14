package com.scatler.rrweb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @GetMapping("/")
    public String index(Model model, Authentication au, HttpServletRequest request) {

        String role_admin = au
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findAny()
                .orElse("ROLE_ADMIN");
        if (role_admin.equals("ROLE_ADMIN"))
            request.getSession().setAttribute("isAdmin",true);

        model.addAttribute("message", "You are logged in as " + au.getName());
        return "index";
    }
}
