/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexkpc
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findById", query = "SELECT t FROM Ticket t WHERE t.id = :id"),
    @NamedQuery(name = "Ticket.findByName", query = "SELECT t FROM Ticket t WHERE t.name = :name"),
    @NamedQuery(name = "Ticket.findBySurname", query = "SELECT t FROM Ticket t WHERE t.surname = :surname"),
    @NamedQuery(name = "Ticket.findByBirthday", query = "SELECT t FROM Ticket t WHERE t.birthday = :birthday")})
public class Ticket extends AbstractEntity implements Serializable {

    //private static final long serialVersionUID = 1L;

    @NotEmpty (message = "Name is required")
    @Size(max = 45)
    @Column(name = "name")
    private String name;

    @NotEmpty (message = "Surname is required")
    @Size(max = 45)
    @Column(name = "surname")
    private String surname;

    @NotNull (message = "Birthday is required")
    //@DateTimeFormat
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @JoinColumn(name = "station1_id", referencedColumnName = "id")
    @ManyToOne
    private Station station1Id;
    @JoinColumn(name = "station2_id", referencedColumnName = "id")
    @ManyToOne
    private Station station2Id;
    @JoinColumn(name = "trd", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TrainRouteidDay trd;

    public Ticket() {
    }

    public Ticket(Integer id) {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Station getStation1Id() {
        return station1Id;
    }

    public void setStation1Id(Station station1Id) {
        this.station1Id = station1Id;
    }

    public Station getStation2Id() {
        return station2Id;
    }

    public void setStation2Id(Station station2Id) {
        this.station2Id = station2Id;
    }

    public TrainRouteidDay getTrd() {
        return trd;
    }

    public void setTrd(TrainRouteidDay trd) {
        this.trd = trd;
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
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.entity.Ticket[ id=" + id + " ]";
    }
    
}
