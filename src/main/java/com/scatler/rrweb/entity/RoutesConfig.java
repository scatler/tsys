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

/**
 *
 * @author alexkpc
 */
@Entity
@Table(name = "routes_config")

public class RoutesConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "route_id")
    private int routeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeId")
    private List<Routes> routesList;
    @JoinColumn(name = "r_cgf_type", referencedColumnName = "r_cfg_type")
    @ManyToOne(optional = false)
    private TrainsConfig rCgfType;

    public RoutesConfig() {
    }

    public RoutesConfig(Integer id) {
        this.id = id;
    }

    public RoutesConfig(Integer id, int routeId) {
        this.id = id;
        this.routeId = routeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public List<Routes> getRoutesList() {
        return routesList;
    }

    public void setRoutesList(List<Routes> routesList) {
        this.routesList = routesList;
    }

    public TrainsConfig getRCgfType() {
        return rCgfType;
    }

    public void setRCgfType(TrainsConfig rCgfType) {
        this.rCgfType = rCgfType;
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
        if (!(object instanceof RoutesConfig)) {
            return false;
        }
        RoutesConfig other = (RoutesConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.RoutesConfig[ id=" + id + " ]";
    }
    
}
