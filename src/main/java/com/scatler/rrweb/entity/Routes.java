package com.scatler.rrweb.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Entity
public class Routes {
    private int id;
    private Time arrivalTime;
    private Integer stopMin;
    private Integer routeId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "arrival_time", nullable = true)
    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "stop_min", nullable = true)
    public Integer getStopMin() {
        return stopMin;
    }

    public void setStopMin(Integer stopMin) {
        this.stopMin = stopMin;
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

        Routes routes = (Routes) o;

        if (id != routes.id) return false;
        if (arrivalTime != null ? !arrivalTime.equals(routes.arrivalTime) : routes.arrivalTime != null) return false;
        if (stopMin != null ? !stopMin.equals(routes.stopMin) : routes.stopMin != null) return false;
        if (routeId != null ? !routeId.equals(routes.routeId) : routes.routeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (stopMin != null ? stopMin.hashCode() : 0);
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        return result;
    }
}
