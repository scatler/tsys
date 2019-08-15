package com.scatler.rrweb.controller;

import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.entity.objects.exception.EmailExistsException;
import com.scatler.rrweb.service.interfaces.IRegistrationService;
import com.scatler.rrweb.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@ControllerAdvice
public class AdminController {

    @Autowired
    @Qualifier("userService")
    IService<UserDTO, Integer> userService;

    @Autowired
    @Qualifier("userService")
    IRegistrationService regService;

    @GetMapping("/")
    public String index(Model model, Authentication au, HttpServletRequest request) {

        String role_admin = au
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findAny()
                .orElse("ROLE_ADMIN");
        if (role_admin.equals("ROLE_ADMIN"))
            request.getSession().setAttribute("isAdmin", true);

        model.addAttribute("message", "You are logged in as " + au.getName());
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
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
            return new ModelAndView("successRegister", "user", accountDto);
        }
    }

    private void createUserAccount(UserDTO accountDto, BindingResult result) throws EmailExistsException {
        regService.registerNewUserAccount(accountDto);
    }

    @ExceptionHandler(EmailExistsException.class)
    public ModelAndView handleUsernameNotFoundException(HttpServletRequest request, Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("registration");
        mv.addObject("error",ex.toString());
        mv.addObject("user",new UserDTO());
        return mv;
    }
}