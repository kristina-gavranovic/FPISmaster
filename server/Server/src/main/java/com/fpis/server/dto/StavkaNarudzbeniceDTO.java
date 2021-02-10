/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.dto;

import com.fpis.server.model.StavkaNarudzbenice;


public class StavkaNarudzbeniceDTO {
    
    private Long id;
    private String opisNarudzbenice;
    private Long proizvodId;
    private Long narudzbenicaId;
    private int statusAkcije=0;
    private double kolicina;

    public StavkaNarudzbeniceDTO(StavkaNarudzbenice stavka) {
        this.id=stavka.getStavkaNarudzbenicePK().getRbNarudzbenice();
        this.opisNarudzbenice=stavka.getOpisNarudzbenice();
        this.proizvodId=stavka.getProizvodId().getSifraProizvoda();
        this.narudzbenicaId=stavka.getStavkaNarudzbenicePK().getNarudzbenica();
        this.kolicina=stavka.getKolicina();
    }

    public StavkaNarudzbeniceDTO(Long id, String opisNarudzbenice, Long proizvodId, Long narudzbenicaId,int status,double kol) {
        this.id = id;
        this.opisNarudzbenice = opisNarudzbenice;
        this.proizvodId = proizvodId;
        this.narudzbenicaId = narudzbenicaId;
        this.statusAkcije=status;
        this.kolicina=kol;
    }

    public StavkaNarudzbeniceDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpisNarudzbenice() {
        return opisNarudzbenice;
    }

    public void setOpisNarudzbenice(String opisNarudzbenice) {
        this.opisNarudzbenice = opisNarudzbenice;
    }

    public Long getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(Long proizvodId) {
        this.proizvodId = proizvodId;
    }

    public Long getNarudzbenicaId() {
        return narudzbenicaId;
    }

    public void setNarudzbenicaId(Long narudzbenicaId) {
        this.narudzbenicaId = narudzbenicaId;
    }

    public int getStatusAkcije() {
        return statusAkcije;
    }

    public void setStatusAkcije(int statusAkcije) {
        this.statusAkcije = statusAkcije;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }
    
    
    
    
}
