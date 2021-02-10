/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.service;

import com.fpis.server.model.RokIsporuke;
import com.fpis.server.repository.RokIsporukeReposirory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RokIsporukeService {
    
    
    @Autowired
    private RokIsporukeReposirory reposirory;

    public List<RokIsporuke> vratiSve() {
        return reposirory.findAll();
    }
}
