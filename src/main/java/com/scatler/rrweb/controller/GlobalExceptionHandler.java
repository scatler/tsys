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
    private Logger logger;

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
