package com.wealth.wealthnotification.controller;

import com.wealth.wealthnotification.common.LogUtil;
import com.wealth.wealthnotification.common.MyUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class RootController {

    @GetMapping("/")
    private String root () {
        return "Welcome Notification!!!";
    }



    int i = 1;

    @GetMapping("/latencyCheck")
    private String latencyCheck() {
        System.out.println("latency check ... started");
        MyUtil.sleepForSomeTime();
        System.out.println("latency check ... completed") ;
        return "latencyCheck success";
    }


    @GetMapping("/errorCheck")
    private String errorCheck() {
        System.out.println("error check ... started");

        if (i==1) {
            i = 2 ;
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found .......", new Exception(""));
        } else if (i==2) {
            i = 3;
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request .......", new Exception(""));
        } else if (i==3) {
            i = 4;
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Bad Gateway ......", new Exception(""));
        } else if (i==4) {
            i = 5;
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict .....", new Exception(""));
        } else if (i==5) {
            i = 6;
            throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "Gateway timeout .......", new Exception(""));
        } else {
            i = 1;
        }

        return "errorCheck success";
    }
}
