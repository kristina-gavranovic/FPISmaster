/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.controller;

import com.fpis.server.model.Dobavljac;
import com.fpis.server.service.DobavljacService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/dobavljac")
public class DobavljacController {
    
    @Autowired
    private DobavljacService service;
    
    @GetMapping()
    public List<Dobavljac> list() {
        return  service.vratiSve();
    }
    
    
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}
