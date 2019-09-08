package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.entity.objects.exception.EmailExistsException;
import com.scatler.rrweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Base64;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    //test changing
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
        /*String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();*/
        String role_admin = au
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findAny()
                .orElse("ROLE_ADMIN");
        return au;
    /*    return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];*/
    }

/*    @GetMapping("/login")
    public String login() {
        return "/dev-release/auth.html";
    }*/

/*    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/submitUser")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDTO accountDto,
            BindingResult result, WebRequest request, Errors errors) throws EmailExistsException {
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        } else {
            createUserAccount(accountDto, result);
            return new ModelAndView("login", "success", "User has been registered");
        }
    }*/

/*    private void createUserAccount(UserDTO accountDto, BindingResult result) throws EmailExistsException {
        userService.registerNewUserAccount(accountDto);
    }*/
}
