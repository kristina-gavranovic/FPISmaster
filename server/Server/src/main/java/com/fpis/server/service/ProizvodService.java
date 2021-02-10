/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.service;



import com.fpis.server.model.Proizvod;
import com.fpis.server.repository.ProizvodRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProizvodService {

    @Autowired
    private ProizvodRepository repository;


    public List<Proizvod> vratiSve() {
        return this.repository.findAll();
    }

    

}
