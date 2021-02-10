/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.repository;

import com.fpis.server.model.Dobavljac;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DobavljacRepository extends JpaRepository<Dobavljac, String> {
    
}
