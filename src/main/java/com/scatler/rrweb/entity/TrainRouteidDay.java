/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexkpc
 */
@Entity
@Table(name = "train_routeid_day")
@NamedQueries({
    @NamedQuery(name = "TrainRouteidDay.findAll", query = "SELECT t FROM TrainRouteidDay t")})
public class TrainRouteidDay implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trd")
    private List<Ticket> ticketList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day_dept")
    @Temporal(TemporalType.DATE)
    private Date dayDept;
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    @ManyToOne(optional = false)
    private RouteStation routeId;
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Train trainId;

    public TrainRouteidDay() {
    }

    public TrainRouteidDay(String id) {
        this.id = id;
    }

    public TrainRouteidDay(String id, Date dayDept) {
        this.id = id;
        this.dayDept = dayDept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDayDept() {
        return dayDept;
    }

    public void setDayDept(Date dayDept) {
        this.dayDept = dayDept;
    }

    public RouteStation getRouteId() {
        return routeId;
    }

    public void setRouteId(RouteStation routeId) {
        this.routeId = routeId;
    }

    public Train getTrainId() {
        return trainId;
    }

    public void setTrainId(Train trainId) {
        this.trainId = trainId;
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
        if (!(object instanceof TrainRouteidDay)) {
            return false;
        }
        TrainRouteidDay other = (TrainRouteidDay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.entity.TrainRouteidDay[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
    
}
