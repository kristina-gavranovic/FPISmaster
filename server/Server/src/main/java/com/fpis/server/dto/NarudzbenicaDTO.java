/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.dto;

import com.fpis.server.model.Narudzbenica;
import java.util.List;
import java.util.stream.Collectors;


public class NarudzbenicaDTO {
    
     private Long brojNarudzbenice;
     private int ukupnoNar;
     private Long rokId;
     private String zaposleniId;
     private List<StavkaNarudzbeniceDTO> stavke;

    public NarudzbenicaDTO(Narudzbenica narudzbenica) {
        
        this.brojNarudzbenice=narudzbenica.getBrojNarudzbenice();
        this.ukupnoNar=narudzbenica.getUkupnoNar();
        this.rokId=narudzbenica.getRokId().getRokId();
        this.zaposleniId=narudzbenica.getZaposleni().getJmbg();
        this.stavke=narudzbenica.getStavkaNarudzbeniceList().stream().map(element->new StavkaNarudzbeniceDTO(element)).collect(Collectors.toList());
    }

    public NarudzbenicaDTO(Long brojNarudzbenice, int ukupnoNar, Long rokId, String zaposleniId) {
        this.brojNarudzbenice = brojNarudzbenice;
        this.ukupnoNar = ukupnoNar;
        this.rokId = rokId;
        this.zaposleniId = zaposleniId;
    }

    public NarudzbenicaDTO() {
    }

    public Long getBrojNarudzbenice() {
        return brojNarudzbenice;
    }

    public void setBrojNarudzbenice(Long brojNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
    }

    public int getUkupnoNar() {
        return ukupnoNar;
    }

    public void setUkupnoNar(int ukupnoNar) {
        this.ukupnoNar = ukupnoNar;
    }

    public Long getRokId() {
        return rokId;
    }

    public void setRokId(Long rokId) {
        this.rokId = rokId;
    }

    public String getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(String zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public List<StavkaNarudzbeniceDTO> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaNarudzbeniceDTO> stavke) {
        this.stavke = stavke;
    }
     
     
}
