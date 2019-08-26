package com.scatler.rrweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UpdateDataController {

    @RequestMapping("/foo")
    void sendData(HttpServletResponse response) throws IOException {

        response.sendRedirect("some-url");
    }
}
