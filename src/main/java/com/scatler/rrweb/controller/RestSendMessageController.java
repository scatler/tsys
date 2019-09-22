package com.scatler.rrweb.controller;

import com.scatler.rrweb.service.SenderServiceMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController("sendMessage")
public class RestSendMessageController {
    @Autowired
    private SenderServiceMQ mq;

    @RequestMapping("/sendMessage")
    public String sendMessageToMQ() throws IOException, TimeoutException {
        mq.send("Update");
        return "send";
    }
}
