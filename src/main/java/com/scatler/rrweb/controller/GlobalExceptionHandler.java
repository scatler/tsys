package com.scatler.rrweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.entity.objects.exception.EmailExistsException;
import com.scatler.rrweb.entity.objects.exception.FoundSamePassengerException;
import com.scatler.rrweb.entity.objects.exception.NoMoreFreeSeatsException;
import com.scatler.rrweb.entity.objects.exception.NotEnoughTimeBeforeDeparture;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    Logger logger;

/*    @Autowired
    private ExceptionTranslatorService exceptionTranslatorService;*/
    //ToDO enable
/*    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex) {
        logger.info("SQLException Occured:: URL=" + request.getRequestURL());
        return "database_error";
    }*/

/*    @ExceptionHandler(HibernateException.class)
    public String handleHibernate(HttpServletRequest request, Exception ex) {
        logger.info("SQLException Occured:: URL=" + request.getRequestURL());
        return "Hibernate_error";
    }*/

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
    @ExceptionHandler(IOException.class)
    public String handleIOException() {
        logger.error("IOException handler executed");
        return "404";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        return "404";
    }

    @ExceptionHandler(EmailExistsException.class)
    public ModelAndView handleEmailExists(HttpServletRequest request, EmailExistsException ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ex.getViewname());
        mv.addObject("error", ex.toString());
        mv.addObject("user", new UserDTO());
        return mv;
    }

 /*   @ExceptionHandler(FoundSamePassengerException.class)
    public ModelAndView handleUsernameNotFoundException(HttpServletRequest request, Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ticket-buy-valid");
        mv.addObject("error", ex.toString());
        mv.addObject("ticket", new TicketDTO());
        return mv;
    }
*/

    @ExceptionHandler(FoundSamePassengerException.class)
    protected ResponseEntity<String> handleUsernameNotFoundException(HttpServletRequest request, Exception ex) {
        logger.warn("Same passenger handled");
        return new ResponseEntity<String>("{\"error\":\"Same passenger found\"}", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotEnoughTimeBeforeDeparture.class)
    public ResponseEntity<String> handleNotEnoughTime(HttpServletRequest request, Exception ex) {
        logger.warn("Not enough time before train departure");
        return new ResponseEntity<String>("{\"error\":\"Not enough time before train departure\"}", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NoMoreFreeSeatsException.class)
    public ResponseEntity<String> handleNoMoreFreeSeats(HttpServletRequest request, Exception ex) {
        logger.warn("No more free seats exception");
        return new ResponseEntity<String>("{\"error\":\"Sorry but you late, all tickets has been sold\"}", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
