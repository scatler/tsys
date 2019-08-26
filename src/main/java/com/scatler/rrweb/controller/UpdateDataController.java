package com.scatler.rrweb.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController("webapi")
public class UpdateDataController {
    @RequestMapping("/update")
    public void sendData(HttpServletResponse response) throws IOException {
        response.sendRedirect("some-url");
    }

    @RequestMapping("/register/{stationId}")
    public void registerListener(@PathVariable Integer stationId) {

    }
}
