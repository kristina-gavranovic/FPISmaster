/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.service;

import com.fpis.server.dto.ZahtevZaKatalogDTO;
import com.fpis.server.model.ZahtevZaKatalog;
import com.fpis.server.repository.DobavljacRepository;
import com.fpis.server.repository.ZahtevZaKatalogRepository;
import com.fpis.server.repository.ZaposleniRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ZahtevZaKatalogService {

    @Autowired
    private ZahtevZaKatalogRepository repository;
    @Autowired
    private DobavljacRepository dobavljacRepository;
    @Autowired
    private ZaposleniRepository zaposleniRepository;

    public List<ZahtevZaKatalog> vratiSve() {
        return this.repository.findAll();
    }

    public ZahtevZaKatalog vratiJedan(String id) {
        return this.repository.getOne(new Long(id));
    }

    public void obrisi(String id) {
        this.repository.deleteById(new Long(id));
    }

    public ZahtevZaKatalog izmeni(ZahtevZaKatalogDTO input, String id) {

        ZahtevZaKatalog zahtev = this.repository.getOne(new Long(id));
        if (input.getDobavljacId() != null) {
            zahtev.setDobavljac(this.dobavljacRepository.getOne(input.getDobavljacId()));
        }
        if (input.getImeKataloga() != null) {
            zahtev.setImeKataloga(input.getImeKataloga());
        }
        if (input.getZaposleniId() != null) {
            zahtev.setZaposleni(this.zaposleniRepository.getOne(input.getZaposleniId()));
        }
        zahtev=this.repository.save(zahtev);
        return zahtev;
    }

    public ZahtevZaKatalog kreiraj(ZahtevZaKatalogDTO input) {

        ZahtevZaKatalog zahtev = new ZahtevZaKatalog();
        zahtev.setBrojKataloga(null);
        zahtev.setDobavljac(this.dobavljacRepository.getOne(input.getDobavljacId()));
        zahtev.setImeKataloga(input.getImeKataloga());
        zahtev.setZaposleni(this.zaposleniRepository.getOne(input.getZaposleniId()));
        zahtev=this.repository.save(zahtev);
        return zahtev;
    }

}
