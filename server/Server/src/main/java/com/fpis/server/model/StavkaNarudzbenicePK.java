/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class StavkaNarudzbenicePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "rb_narudzbenice")
    private long rbNarudzbenice;
    @Basic(optional = false)
    @Column(name = "narudzbenica")
    private long narudzbenica;

    public StavkaNarudzbenicePK() {
    }

    public StavkaNarudzbenicePK(long rbNarudzbenice, long narudzbenica) {
        this.rbNarudzbenice = rbNarudzbenice;
        this.narudzbenica = narudzbenica;
    }

    public long getRbNarudzbenice() {
        return rbNarudzbenice;
    }

    public void setRbNarudzbenice(long rbNarudzbenice) {
        this.rbNarudzbenice = rbNarudzbenice;
    }

    public long getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(long narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) rbNarudzbenice;
        hash += (int) narudzbenica;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkaNarudzbenicePK)) {
            return false;
        }
        StavkaNarudzbenicePK other = (StavkaNarudzbenicePK) object;
        if (this.rbNarudzbenice != other.rbNarudzbenice) {
            return false;
        }
        if (this.narudzbenica != other.narudzbenica) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fpis.server.model.StavkaNarudzbenicePK[ rbNarudzbenice=" + rbNarudzbenice + ", narudzbenica=" + narudzbenica + " ]";
    }
    
}
