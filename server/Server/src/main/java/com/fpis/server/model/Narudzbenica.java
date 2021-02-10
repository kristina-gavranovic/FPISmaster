/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "narudzbenica", catalog = "fpis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Narudzbenica.findAll", query = "SELECT n FROM Narudzbenica n"),
    @NamedQuery(name = "Narudzbenica.findByBrojNarudzbenice", query = "SELECT n FROM Narudzbenica n WHERE n.brojNarudzbenice = :brojNarudzbenice"),
    @NamedQuery(name = "Narudzbenica.findByUkupnoNar", query = "SELECT n FROM Narudzbenica n WHERE n.ukupnoNar = :ukupnoNar")})
public class Narudzbenica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "broj_narudzbenice")
    private Long brojNarudzbenice;
    @Basic(optional = false)
    @Column(name = "ukupno_nar")
    private int ukupnoNar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "narudzbenica1")
    private List<StavkaNarudzbenice> stavkaNarudzbeniceList;
    @JoinColumn(name = "rok_id", referencedColumnName = "rok_id")
    @ManyToOne
    private RokIsporuke rokId;
    @JoinColumn(name = "zaposleni", referencedColumnName = "jmbg")
    @ManyToOne
    private Zaposleni zaposleni;

    public Narudzbenica() {
    }

    public Narudzbenica(Long brojNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
    }

    public Narudzbenica(Long brojNarudzbenice, int ukupnoNar) {
        this.brojNarudzbenice = brojNarudzbenice;
        this.ukupnoNar = ukupnoNar;
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

    @XmlTransient
    public List<StavkaNarudzbenice> getStavkaNarudzbeniceList() {
        return stavkaNarudzbeniceList;
    }

    public void setStavkaNarudzbeniceList(List<StavkaNarudzbenice> stavkaNarudzbeniceList) {
        this.stavkaNarudzbeniceList = stavkaNarudzbeniceList;
    }

    public RokIsporuke getRokId() {
        return rokId;
    }

    public void setRokId(RokIsporuke rokId) {
        this.rokId = rokId;
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
        hash += (brojNarudzbenice != null ? brojNarudzbenice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Narudzbenica)) {
            return false;
        }
        Narudzbenica other = (Narudzbenica) object;
        if ((this.brojNarudzbenice == null && other.brojNarudzbenice != null) || (this.brojNarudzbenice != null && !this.brojNarudzbenice.equals(other.brojNarudzbenice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fpis.server.model.Narudzbenica[ brojNarudzbenice=" + brojNarudzbenice + " ]";
    }
    
}
