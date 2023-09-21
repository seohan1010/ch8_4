package com.seohan1010.ch8_4.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @RequestMapping(value="/",method= RequestMethod.GET)
    public ResponseEntity<String> main(){

        return new ResponseEntity<String>("hello",HttpStatus.OK);
    }

}
