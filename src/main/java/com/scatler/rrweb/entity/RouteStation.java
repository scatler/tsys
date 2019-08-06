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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "route_station")
@NamedQueries({
    @NamedQuery(name = "RouteStation.findAll", query = "SELECT r FROM RouteStation r")})
public class RouteStation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIME)
    private Date arrivalTime;
    @Column(name = "stop_min")
    @Temporal(TemporalType.TIME)
    private Date stopMin;
    @Column(name = "day")
    private Integer day;
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Route routeId;
    @JoinColumn(name = "station_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Station stationId;

    public RouteStation() {
    }

    public RouteStation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getStopMin() {
        return stopMin;
    }

    public void setStopMin(Date stopMin) {
        this.stopMin = stopMin;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }

    public Station getStationId() {
        return stationId;
    }

    public void setStationId(Station stationId) {
        this.stationId = stationId;
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
        if (!(object instanceof RouteStation)) {
            return false;
        }
        RouteStation other = (RouteStation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.entity.RouteStation[ id=" + id + " ]";
    }
    
}
