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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "proizvod", catalog = "fpis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proizvod.findAll", query = "SELECT p FROM Proizvod p"),
    @NamedQuery(name = "Proizvod.findBySifraProizvoda", query = "SELECT p FROM Proizvod p WHERE p.sifraProizvoda = :sifraProizvoda"),
    @NamedQuery(name = "Proizvod.findByNazivProizvoda", query = "SELECT p FROM Proizvod p WHERE p.nazivProizvoda = :nazivProizvoda"),
    @NamedQuery(name = "Proizvod.findByIznosProizvoda", query = "SELECT p FROM Proizvod p WHERE p.iznosProizvoda = :iznosProizvoda")})
public class Proizvod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sifra_proizvoda")
    private Long sifraProizvoda;
    @Basic(optional = false)
    @Column(name = "naziv_proizvoda")
    private String nazivProizvoda;
    @Basic(optional = false)
    @Column(name = "iznos_proizvoda")
    private int iznosProizvoda;
   
    public Proizvod() {
    }

    public Proizvod(Long sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    public Proizvod(Long sifraProizvoda, String nazivProizvoda, int iznosProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
        this.nazivProizvoda = nazivProizvoda;
        this.iznosProizvoda = iznosProizvoda;
    }

    public Long getSifraProizvoda() {
        return sifraProizvoda;
    }

    public void setSifraProizvoda(Long sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public int getIznosProizvoda() {
        return iznosProizvoda;
    }

    public void setIznosProizvoda(int iznosProizvoda) {
        this.iznosProizvoda = iznosProizvoda;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraProizvoda != null ? sifraProizvoda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proizvod)) {
            return false;
        }
        Proizvod other = (Proizvod) object;
        if ((this.sifraProizvoda == null && other.sifraProizvoda != null) || (this.sifraProizvoda != null && !this.sifraProizvoda.equals(other.sifraProizvoda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fpis.server.model.Proizvod[ sifraProizvoda=" + sifraProizvoda + " ]";
    }
    
}
