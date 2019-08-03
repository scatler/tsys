/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import javax.validation.constraints.NotNull;

/**
 *
 * @author alexkpc
 */
@Entity
@Table(name = "routes")
@NamedQueries({
    @NamedQuery(name = "Routes.findAll", query = "SELECT r FROM Routes r")})
public class Routes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    //TODO check column type
    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIME)
    private Date arrivalTime;

    @Column(name = "stop_min")
    private Integer stopMin;
    @Basic(optional = false)
    @NotNull

    @Column(name = "station_id")
    private int stationId;

    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    @ManyToOne(optional = false)
    private RoutesConfig routeId;

    //**
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    private List<Stations> stations;

    //inverse
    @JoinColumn(name = "station_id", referencedColumnName = "station_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Stations station;

    public Routes() {
    }

    public Routes(Integer id) {
        this.id = id;
    }

    public Routes(Integer id, int stationId) {
        this.id = id;
        this.stationId = stationId;
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

    public Integer getStopMin() {
        return stopMin;
    }

    public void setStopMin(Integer stopMin) {
        this.stopMin = stopMin;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public RoutesConfig getRouteId() {
        return routeId;
    }

    public void setRouteId(RoutesConfig routeId) {
        this.routeId = routeId;
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
        if (!(object instanceof Routes)) {
            return false;
        }
        Routes other = (Routes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.Routes[ id=" + id + " ]";
    }
    
}
