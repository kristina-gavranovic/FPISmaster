/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpis.server.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "rok_isporuke", catalog = "fpis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RokIsporuke.findAll", query = "SELECT r FROM RokIsporuke r"),
    @NamedQuery(name = "RokIsporuke.findByRokId", query = "SELECT r FROM RokIsporuke r WHERE r.rokId = :rokId"),
    @NamedQuery(name = "RokIsporuke.findByBrojDana", query = "SELECT r FROM RokIsporuke r WHERE r.brojDana = :brojDana")})
public class RokIsporuke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rok_id")
    private Long rokId;
    @Basic(optional = false)
    @Column(name = "broj_dana")
    private int brojDana;
   

    public RokIsporuke() {
    }

    public RokIsporuke(Long rokId) {
        this.rokId = rokId;
    }

    public RokIsporuke(Long rokId, int brojDana) {
        this.rokId = rokId;
        this.brojDana = brojDana;
    }

    public Long getRokId() {
        return rokId;
    }

    public void setRokId(Long rokId) {
        this.rokId = rokId;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rokId != null ? rokId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RokIsporuke)) {
            return false;
        }
        RokIsporuke other = (RokIsporuke) object;
        if ((this.rokId == null && other.rokId != null) || (this.rokId != null && !this.rokId.equals(other.rokId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fpis.server.model.RokIsporuke[ rokId=" + rokId + " ]";
    }
    
}
