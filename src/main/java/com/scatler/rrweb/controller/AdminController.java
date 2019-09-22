package com.scatler.rrweb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class AdminController {
    @GetMapping("/")
    public String index(Model model, Authentication au, HttpServletRequest request) {
        if (au != null) {
            String role_admin = au
                    .getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .findAny()
                    .orElse("ROLE_ADMIN");
            if (role_admin.equals("ROLE_ADMIN"))
                request.getSession().setAttribute("isAdmin", true);
            model.addAttribute("message", "You are logged in as " + au.getName());
        } else {
            return "/dev-release/auth.html";
        }
        return "forward:/dev-release/index.html";
    }

    @RequestMapping(value = "/user", produces = "application/json")
    @ResponseBody
    public Principal user(HttpServletRequest request, Authentication au) {
        String role_admin = au
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findAny()
                .orElse("ROLE_ADMIN");
        return au;
    }
}
