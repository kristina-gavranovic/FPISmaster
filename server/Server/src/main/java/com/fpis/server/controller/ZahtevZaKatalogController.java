/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.controller;

import com.fpis.server.dto.ZahtevZaKatalogDTO;
import com.fpis.server.model.ZahtevZaKatalog;
import com.fpis.server.service.ZahtevZaKatalogService;
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
@RequestMapping("/zahtev")
public class ZahtevZaKatalogController {
    
    @Autowired
    private ZahtevZaKatalogService service;
    
    @GetMapping()
    public List<ZahtevZaKatalogDTO> list() {
        List<ZahtevZaKatalog> katalozi=this.service.vratiSve();
        return  katalozi.stream().map(element->new ZahtevZaKatalogDTO(element)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ZahtevZaKatalogDTO get(@PathVariable String id) {
        return new ZahtevZaKatalogDTO(this.service.vratiJedan(id));
    }
    
    @PatchMapping("/{id}")
    public ZahtevZaKatalogDTO put(@PathVariable String id, @RequestBody ZahtevZaKatalogDTO input) {
        
        return new ZahtevZaKatalogDTO(this.service.izmeni(input,id));
    }
    
    @PostMapping
    public ZahtevZaKatalogDTO post(@RequestBody ZahtevZaKatalogDTO input) {
        
        return new ZahtevZaKatalogDTO(this.service.kreiraj(input));
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
