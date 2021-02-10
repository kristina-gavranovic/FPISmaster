/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.service;

import com.fpis.server.model.Zaposleni;
import com.fpis.server.repository.ZaposleniRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ZaposleniService {
    
    @Autowired
    private ZaposleniRepository repository;

    public List<Zaposleni> vratiSve() {
        return repository.findAll();
    }
}
