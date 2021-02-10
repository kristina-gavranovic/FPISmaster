/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.controller;

import com.fpis.server.dto.NarudzbenicaDTO;
import com.fpis.server.model.Narudzbenica;
import com.fpis.server.service.NarudzbenicaService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/narudzbenica")
public class NarudzbenicaController {
    
    @Autowired
    private NarudzbenicaService service;
    
    @GetMapping()
    public List<NarudzbenicaDTO> list() {
        List<Narudzbenica> lista=this.service.vratiSve();
        return lista.stream().map((element)->new NarudzbenicaDTO(element)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        return new NarudzbenicaDTO(this.service.vratiJedan(id));
    }
    
    @PatchMapping("/{id}")
    public NarudzbenicaDTO put(@PathVariable String id, @RequestBody NarudzbenicaDTO input) {
        
        return new NarudzbenicaDTO(this.service.izmeni(input,id));
    }
    
    @PostMapping
    public NarudzbenicaDTO post(@RequestBody NarudzbenicaDTO input) {
        return new NarudzbenicaDTO(this.service.kreiraj(input));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        this.service.obrisi(id);
        return ResponseEntity.ok("uspeh");
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}
