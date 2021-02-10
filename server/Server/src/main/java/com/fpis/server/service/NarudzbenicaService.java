/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.service;

import com.fpis.server.dto.NarudzbenicaDTO;
import com.fpis.server.dto.StavkaNarudzbeniceDTO;
import com.fpis.server.model.Narudzbenica;
import com.fpis.server.model.StavkaNarudzbenice;
import com.fpis.server.model.StavkaNarudzbenicePK;
import com.fpis.server.repository.NarudzbenicaRepository;
import com.fpis.server.repository.ProizvodRepository;
import com.fpis.server.repository.RokIsporukeReposirory;
import com.fpis.server.repository.StavkaRepository;
import com.fpis.server.repository.ZaposleniRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class NarudzbenicaService {

    @Autowired
    private NarudzbenicaRepository repository;

    @Autowired
    private RokIsporukeReposirory rokRepository;

    @Autowired
    private ZaposleniRepository zaposleniRepository;

    @Autowired
    private ProizvodRepository proizvodRepository;

    @Autowired
    private StavkaRepository stavkaRepository;

    public List<Narudzbenica> vratiSve() {
        return this.repository.findAll();
    }

    public Narudzbenica vratiJedan(String id) {
        return this.repository.getOne(new Long(id));
    }

    public void obrisi(String id) {
        this.repository.deleteById(new Long(id));
    }

    public Narudzbenica izmeni(NarudzbenicaDTO input, String id) {
        System.out.println(id);
        Narudzbenica narudzbenica = this.repository.getOne(new Long(id));
        System.out.println(narudzbenica);
        if (input.getRokId() != null) {
            narudzbenica.setRokId(this.rokRepository.getOne(input.getRokId()));
        }
        if (input.getZaposleniId() != null) {
            narudzbenica.setZaposleni(this.zaposleniRepository.getOne(input.getZaposleniId()));
        }
        if (input.getUkupnoNar() > 0) {
            narudzbenica.setUkupnoNar(input.getUkupnoNar());
        }
        narudzbenica = this.repository.save(narudzbenica);
        long index = 1;
        if (narudzbenica.getStavkaNarudzbeniceList() == null) {
            narudzbenica.setStavkaNarudzbeniceList(new LinkedList<>());
        }
        if (narudzbenica.getStavkaNarudzbeniceList().size() > 0) {
            index = this.getMaxId(narudzbenica.getStavkaNarudzbeniceList());
        }
        System.out.println(index);
        for (StavkaNarudzbeniceDTO stavkaDTO : input.getStavke()) {
            //status=-1 : BRISANJE
            if (stavkaDTO.getStatusAkcije() == -1) {
                List<StavkaNarudzbenice> nova = narudzbenica.getStavkaNarudzbeniceList().stream().filter(element -> element.getStavkaNarudzbenicePK().getRbNarudzbenice() != stavkaDTO.getId()).collect(Collectors.toList());
                stavkaRepository.deleteById(new StavkaNarudzbenicePK(stavkaDTO.getId(), narudzbenica.getBrojNarudzbenice()));
                narudzbenica.setStavkaNarudzbeniceList(nova);
                continue;
            }
            //dodaj novu
            if (stavkaDTO.getStatusAkcije() == 0) {
                index++;
                StavkaNarudzbenice stavka = new StavkaNarudzbenice();
                stavka.setProizvodId(this.proizvodRepository.getOne(stavkaDTO.getProizvodId()));
                stavka.setOpisNarudzbenice(stavkaDTO.getOpisNarudzbenice());
                stavka.setNarudzbenica1(narudzbenica);
                stavka.setKolicina(stavkaDTO.getKolicina());
                stavka.setStavkaNarudzbenicePK(new StavkaNarudzbenicePK(index, narudzbenica.getBrojNarudzbenice()));
                narudzbenica.getStavkaNarudzbeniceList().add(stavka);
                continue;
            }
           //izmeni  postojecu
            if (stavkaDTO.getStatusAkcije() == 1) {
                for (StavkaNarudzbenice stavkaNarudzbenice : narudzbenica.getStavkaNarudzbeniceList()) {
                    if (stavkaNarudzbenice.getStavkaNarudzbenicePK().getRbNarudzbenice() == stavkaDTO.getId()) {
                        if (stavkaDTO.getOpisNarudzbenice() != null) {
                            stavkaNarudzbenice.setOpisNarudzbenice(stavkaDTO.getOpisNarudzbenice());
                        }
                        if (stavkaDTO.getProizvodId() != null) {
                            stavkaNarudzbenice.setProizvodId(this.proizvodRepository.getOne(stavkaDTO.getProizvodId()));
                        }
                        if (stavkaDTO.getKolicina() > 0) {
                            stavkaNarudzbenice.setKolicina(stavkaDTO.getKolicina());
                        }
                        
                    }
                }
            }
        }
        narudzbenica = this.repository.save(narudzbenica);

        return narudzbenica;
    }

    public Narudzbenica kreiraj(NarudzbenicaDTO input) {

        Narudzbenica narudzbenica = new Narudzbenica();
        narudzbenica.setUkupnoNar(input.getUkupnoNar());
        narudzbenica.setBrojNarudzbenice(null);
        narudzbenica.setRokId(this.rokRepository.getOne(input.getRokId()));
        narudzbenica.setZaposleni(this.zaposleniRepository.getOne(input.getZaposleniId()));
        narudzbenica.setStavkaNarudzbeniceList(new LinkedList<>());
        narudzbenica = this.repository.save(narudzbenica);
        long index = 0;
        for (StavkaNarudzbeniceDTO stavkaDTO : input.getStavke()) {
            index++;
            StavkaNarudzbenice stavka = new StavkaNarudzbenice();
            stavka.setStavkaNarudzbenicePK(new StavkaNarudzbenicePK());
            stavka.getStavkaNarudzbenicePK().setRbNarudzbenice(index);
            stavka.setKolicina(stavkaDTO.getKolicina());
            stavka.getStavkaNarudzbenicePK().setNarudzbenica(narudzbenica.getBrojNarudzbenice());
            stavka.setNarudzbenica1(narudzbenica);
           
            stavka.setOpisNarudzbenice(stavkaDTO.getOpisNarudzbenice());
            stavka.setProizvodId(this.proizvodRepository.getOne(stavkaDTO.getProizvodId()));
            narudzbenica.getStavkaNarudzbeniceList().add(stavka);
        }
        this.repository.save(narudzbenica);

        return narudzbenica;
    }

    private long getMaxId(List<StavkaNarudzbenice> stavke) {
        long max = 0;
        for (StavkaNarudzbenice stavkaNarudzbenice : stavke) {
            if (stavkaNarudzbenice.getStavkaNarudzbenicePK().getRbNarudzbenice() > max) {

                max = stavkaNarudzbenice.getStavkaNarudzbenicePK().getRbNarudzbenice();
            }
        }
        return max;
    }
}
