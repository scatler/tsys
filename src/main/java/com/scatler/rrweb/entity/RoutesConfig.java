package com.scatler.rrweb.entity;

import javax.persistence.*;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Entity
@Table(name = "routes_config", schema = "mydatabase", catalog = "")
@IdClass(RoutesConfigPK.class)
public class RoutesConfig {
    private int id;
    private int rCgfType;
    private Integer routeId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "r_cgf_type", nullable = false)
    public int getrCgfType() {
        return rCgfType;
    }

    public void setrCgfType(int rCgfType) {
        this.rCgfType = rCgfType;
    }

    @Basic
    @Column(name = "route_id", nullable = true)
    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutesConfig that = (RoutesConfig) o;

        if (id != that.id) return false;
        if (rCgfType != that.rCgfType) return false;
        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + rCgfType;
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        return result;
    }
}
