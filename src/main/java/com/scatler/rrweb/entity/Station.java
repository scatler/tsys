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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexkpc
 */
@Entity
@Table(name = "station")
@NamedQueries({
    @NamedQuery(name = "Station.findAll", query = "SELECT s FROM Station s")})
public class Station implements Serializable {

    @OneToMany(mappedBy = "station1Id")
    private List<Ticket> ticketList;
    @OneToMany(mappedBy = "station2Id")
    private List<Ticket> ticketList1;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Column(name = "timezone")
    private Integer timezone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stationId")
    private List<RouteStation> routeStationList;
    @JoinColumn(name = "line_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Line lineId;

    public Station() {
    }

    public Station(Integer id) {
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

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public List<RouteStation> getRouteStationList() {
        return routeStationList;
    }

    public void setRouteStationList(List<RouteStation> routeStationList) {
        this.routeStationList = routeStationList;
    }

    public Line getLineId() {
        return lineId;
    }

    public void setLineId(Line lineId) {
        this.lineId = lineId;
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
        if (!(object instanceof Station)) {
            return false;
        }
        Station other = (Station) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.entity.Station[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @XmlTransient
    public List<Ticket> getTicketList1() {
        return ticketList1;
    }

    public void setTicketList1(List<Ticket> ticketList1) {
        this.ticketList1 = ticketList1;
    }
    
}
