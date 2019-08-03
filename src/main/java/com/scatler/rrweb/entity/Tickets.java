/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexkpc
 */
@Entity
@Table(name = "tickets")
@NamedQueries({
    @NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t")})
public class Tickets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "train_id")
    private Integer trainId;
    @Column(name = "route_id")
    private Integer routeId;
    @Column(name = "day")
    @Temporal(TemporalType.DATE)
    private Date day;
    @Column(name = "tickents_amount")
    private Integer tickentsAmount;
    @Column(name = "stationid_start")
    private Integer stationidStart;
    @Column(name = "stationid_end")
    private Integer stationidEnd;

    public Tickets() {
    }

    public Tickets(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getTickentsAmount() {
        return tickentsAmount;
    }

    public void setTickentsAmount(Integer tickentsAmount) {
        this.tickentsAmount = tickentsAmount;
    }

    public Integer getStationidStart() {
        return stationidStart;
    }

    public void setStationidStart(Integer stationidStart) {
        this.stationidStart = stationidStart;
    }

    public Integer getStationidEnd() {
        return stationidEnd;
    }

    public void setStationidEnd(Integer stationidEnd) {
        this.stationidEnd = stationidEnd;
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
        if (!(object instanceof Tickets)) {
            return false;
        }
        Tickets other = (Tickets) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.Tickets[ id=" + id + " ]";
    }
    
}
