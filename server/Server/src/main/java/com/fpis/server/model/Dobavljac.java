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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "dobavljac", catalog = "fpis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dobavljac.findAll", query = "SELECT d FROM Dobavljac d"),
    @NamedQuery(name = "Dobavljac.findByPibDobavljaca", query = "SELECT d FROM Dobavljac d WHERE d.pibDobavljaca = :pibDobavljaca"),
    @NamedQuery(name = "Dobavljac.findByNazivDobavljaca", query = "SELECT d FROM Dobavljac d WHERE d.nazivDobavljaca = :nazivDobavljaca"),
    @NamedQuery(name = "Dobavljac.findByTelefonDobavljaca", query = "SELECT d FROM Dobavljac d WHERE d.telefonDobavljaca = :telefonDobavljaca"),
    @NamedQuery(name = "Dobavljac.findByEmailDobavljaca", query = "SELECT d FROM Dobavljac d WHERE d.emailDobavljaca = :emailDobavljaca")})
public class Dobavljac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pib_dobavljaca")
    private String pibDobavljaca;
    @Basic(optional = false)
    @Column(name = "naziv_dobavljaca")
    private String nazivDobavljaca;
    @Basic(optional = false)
    @Column(name = "telefon_dobavljaca")
    private String telefonDobavljaca;
    @Basic(optional = false)
    @Column(name = "email_dobavljaca")
    private String emailDobavljaca;
   

    public Dobavljac() {
    }

    public Dobavljac(String pibDobavljaca) {
        this.pibDobavljaca = pibDobavljaca;
    }

    public Dobavljac(String pibDobavljaca, String nazivDobavljaca, String telefonDobavljaca, String emailDobavljaca) {
        this.pibDobavljaca = pibDobavljaca;
        this.nazivDobavljaca = nazivDobavljaca;
        this.telefonDobavljaca = telefonDobavljaca;
        this.emailDobavljaca = emailDobavljaca;
    }

    public String getPibDobavljaca() {
        return pibDobavljaca;
    }

    public void setPibDobavljaca(String pibDobavljaca) {
        this.pibDobavljaca = pibDobavljaca;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public String getTelefonDobavljaca() {
        return telefonDobavljaca;
    }

    public void setTelefonDobavljaca(String telefonDobavljaca) {
        this.telefonDobavljaca = telefonDobavljaca;
    }

    public String getEmailDobavljaca() {
        return emailDobavljaca;
    }

    public void setEmailDobavljaca(String emailDobavljaca) {
        this.emailDobavljaca = emailDobavljaca;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pibDobavljaca != null ? pibDobavljaca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Dobavljac)) {
            return false;
        }
        Dobavljac other = (Dobavljac) object;
        if ((this.pibDobavljaca == null && other.pibDobavljaca != null) || (this.pibDobavljaca != null && !this.pibDobavljaca.equals(other.pibDobavljaca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fpis.server.model.Dobavljac[ pibDobavljaca=" + pibDobavljaca + " ]";
    }
    
}
