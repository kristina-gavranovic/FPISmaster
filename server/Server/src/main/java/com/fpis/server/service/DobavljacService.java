/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.service;


import com.fpis.server.model.Dobavljac;
import com.fpis.server.repository.DobavljacRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DobavljacService {

    @Autowired
    private DobavljacRepository repository;
    
    

    

    public List<Dobavljac> vratiSve() {
        return this.repository.findAll();
    }

   

    
}
