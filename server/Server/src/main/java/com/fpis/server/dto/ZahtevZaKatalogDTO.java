/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.dto;

import com.fpis.server.model.ZahtevZaKatalog;


public class ZahtevZaKatalogDTO {

    private Long brojKataloga;
    private String imeKataloga;
    private String zaposleniId;
    private String dobavljacId;

    public ZahtevZaKatalogDTO(ZahtevZaKatalog zahtev) {
        this.brojKataloga=zahtev.getBrojKataloga();
        this.imeKataloga=zahtev.getImeKataloga();
        this.zaposleniId=zahtev.getZaposleni().getJmbg();
        this.dobavljacId=zahtev.getDobavljac().getPibDobavljaca();
    }

    public ZahtevZaKatalogDTO() {
    }

    public ZahtevZaKatalogDTO(Long brojKataloga, String imeKataloga, String zaposleniId, String dobavljacId) {
        this.brojKataloga = brojKataloga;
        this.imeKataloga = imeKataloga;
        this.zaposleniId = zaposleniId;
        this.dobavljacId = dobavljacId;
    }

    public Long getBrojKataloga() {
        return brojKataloga;
    }

    public void setBrojKataloga(Long brojKataloga) {
        this.brojKataloga = brojKataloga;
    }

    public String getImeKataloga() {
        return imeKataloga;
    }

    public void setImeKataloga(String imeKataloga) {
        this.imeKataloga = imeKataloga;
    }

    public String getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(String zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public String getDobavljacId() {
        return dobavljacId;
    }

    public void setDobavljacId(String dobavljacId) {
        this.dobavljacId = dobavljacId;
    }
    
    
}
