/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "zahtev_za_katalog", catalog = "fpis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ZahtevZaKatalog.findAll", query = "SELECT z FROM ZahtevZaKatalog z"),
    @NamedQuery(name = "ZahtevZaKatalog.findByBrojKataloga", query = "SELECT z FROM ZahtevZaKatalog z WHERE z.brojKataloga = :brojKataloga"),
    @NamedQuery(name = "ZahtevZaKatalog.findByImeKataloga", query = "SELECT z FROM ZahtevZaKatalog z WHERE z.imeKataloga = :imeKataloga")})
public class ZahtevZaKatalog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "broj_kataloga")
    private Long brojKataloga;
    @Basic(optional = false)
    @Column(name = "ime_kataloga")
    private String imeKataloga;
    @JoinColumn(name = "dobavljac", referencedColumnName = "pib_dobavljaca")
    @ManyToOne
    private Dobavljac dobavljac;
    @JoinColumn(name = "zaposleni", referencedColumnName = "jmbg")
    @ManyToOne
    private Zaposleni zaposleni;

    public ZahtevZaKatalog() {
    }

    public ZahtevZaKatalog(Long brojKataloga) {
        this.brojKataloga = brojKataloga;
    }

    public ZahtevZaKatalog(Long brojKataloga, String imeKataloga) {
        this.brojKataloga = brojKataloga;
        this.imeKataloga = imeKataloga;
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

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brojKataloga != null ? brojKataloga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZahtevZaKatalog)) {
            return false;
        }
        ZahtevZaKatalog other = (ZahtevZaKatalog) object;
        if ((this.brojKataloga == null && other.brojKataloga != null) || (this.brojKataloga != null && !this.brojKataloga.equals(other.brojKataloga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fpis.server.model.ZahtevZaKatalog[ brojKataloga=" + brojKataloga + " ]";
    }
    
}
