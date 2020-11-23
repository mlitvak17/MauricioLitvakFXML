/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mauriciolitvak
 */
@Entity
@Table(name = "TICKETMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticketmodel.findAll", query = "SELECT t FROM Ticketmodel t")
    , @NamedQuery(name = "Ticketmodel.findById", query = "SELECT t FROM Ticketmodel t WHERE t.id = :id")
    , @NamedQuery(name = "Ticketmodel.findByName", query = "SELECT t FROM Ticketmodel t WHERE t.name = :name")
    , @NamedQuery(name = "Ticketmodel.findByPrice", query = "SELECT t FROM Ticketmodel t WHERE t.price = :price")
    , @NamedQuery(name = "Ticketmodel.findByPurchased", query = "SELECT t FROM Ticketmodel t WHERE t.purchased = :purchased")})
public class Ticketmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "PURCHASED")
    private Boolean purchased;

    public Ticketmodel() {
    }

    public Ticketmodel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticketmodel)) {
            return false;
        }
        Ticketmodel other = (Ticketmodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ticketmodel[ id=" + id + " ]";
    }
    
}
