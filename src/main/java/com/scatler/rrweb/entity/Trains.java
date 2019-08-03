/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author alexkpc
 */
@Entity
@Table(name = "trains")

public class Trains implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "loco_id")
    private Integer locoId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Column(name = "seats")
    private Integer seats;
    @Column(name = "train_id")
    private Integer trainId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainId")
    private List<TrainsConfig> trainsConfigList;

    public Trains() {
    }

    public Trains(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocoId() {
        return locoId;
    }

    public void setLocoId(Integer locoId) {
        this.locoId = locoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public List<TrainsConfig> getTrainsConfigList() {
        return trainsConfigList;
    }

    public void setTrainsConfigList(List<TrainsConfig> trainsConfigList) {
        this.trainsConfigList = trainsConfigList;
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
        if (!(object instanceof Trains)) {
            return false;
        }
        Trains other = (Trains) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.Trains[ id=" + id + " ]";
    }

}
