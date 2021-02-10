/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "stavka_narudzbenice", catalog = "fpis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StavkaNarudzbenice.findAll", query = "SELECT s FROM StavkaNarudzbenice s"),
    @NamedQuery(name = "StavkaNarudzbenice.findByRbNarudzbenice", query = "SELECT s FROM StavkaNarudzbenice s WHERE s.stavkaNarudzbenicePK.rbNarudzbenice = :rbNarudzbenice"),
    @NamedQuery(name = "StavkaNarudzbenice.findByOpisNarudzbenice", query = "SELECT s FROM StavkaNarudzbenice s WHERE s.opisNarudzbenice = :opisNarudzbenice"),
    @NamedQuery(name = "StavkaNarudzbenice.findByNarudzbenica", query = "SELECT s FROM StavkaNarudzbenice s WHERE s.stavkaNarudzbenicePK.narudzbenica = :narudzbenica")})
public class StavkaNarudzbenice implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StavkaNarudzbenicePK stavkaNarudzbenicePK;
    @Column(name = "opis_narudzbenice")
    private String opisNarudzbenice;
    @Column(name = "kolicina")
    private double kolicina;
    @JoinColumn(name = "narudzbenica", referencedColumnName = "broj_narudzbenice", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Narudzbenica narudzbenica1;
    @JoinColumn(name = "proizvod_id", referencedColumnName = "sifra_proizvoda")
    @ManyToOne(optional = false)
    private Proizvod proizvodId;

    public StavkaNarudzbenice() {
    }

    public StavkaNarudzbenice(StavkaNarudzbenicePK stavkaNarudzbenicePK) {
        this.stavkaNarudzbenicePK = stavkaNarudzbenicePK;
    }

    public StavkaNarudzbenice(long rbNarudzbenice, long narudzbenica) {
        this.stavkaNarudzbenicePK = new StavkaNarudzbenicePK(rbNarudzbenice, narudzbenica);
    }

    public StavkaNarudzbenicePK getStavkaNarudzbenicePK() {
        return stavkaNarudzbenicePK;
    }

    public void setStavkaNarudzbenicePK(StavkaNarudzbenicePK stavkaNarudzbenicePK) {
        this.stavkaNarudzbenicePK = stavkaNarudzbenicePK;
    }

    public String getOpisNarudzbenice() {
        return opisNarudzbenice;
    }

    public void setOpisNarudzbenice(String opisNarudzbenice) {
        this.opisNarudzbenice = opisNarudzbenice;
    }

    public Narudzbenica getNarudzbenica1() {
        return narudzbenica1;
    }

    public void setNarudzbenica1(Narudzbenica narudzbenica1) {
        this.narudzbenica1 = narudzbenica1;
    }

    public Proizvod getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(Proizvod proizvodId) {
        this.proizvodId = proizvodId;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stavkaNarudzbenicePK != null ? stavkaNarudzbenicePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkaNarudzbenice)) {
            return false;
        }
        StavkaNarudzbenice other = (StavkaNarudzbenice) object;
        if ((this.stavkaNarudzbenicePK == null && other.stavkaNarudzbenicePK != null) || (this.stavkaNarudzbenicePK != null && !this.stavkaNarudzbenicePK.equals(other.stavkaNarudzbenicePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fpis.server.model.StavkaNarudzbenice[ stavkaNarudzbenicePK=" + stavkaNarudzbenicePK + " ]";
    }

}
