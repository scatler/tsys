package com.scatler.rrweb.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Entity
public class Tickets {
    private int id;
    private Date day;
    private Integer tickentsAmount;
    private Trains trainsByTrainId;
    private Routes routesByRouteId;
    private Stations stationsByStationidStart;
    private Stations stationsByStationidEnd;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "day", nullable = true)
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Basic
    @Column(name = "tickents_amount", nullable = true)
    public Integer getTickentsAmount() {
        return tickentsAmount;
    }

    public void setTickentsAmount(Integer tickentsAmount) {
        this.tickentsAmount = tickentsAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tickets tickets = (Tickets) o;

        if (id != tickets.id) return false;
        if (day != null ? !day.equals(tickets.day) : tickets.day != null) return false;
        if (tickentsAmount != null ? !tickentsAmount.equals(tickets.tickentsAmount) : tickets.tickentsAmount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (tickentsAmount != null ? tickentsAmount.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    public Trains getTrainsByTrainId() {
        return trainsByTrainId;
    }

    public void setTrainsByTrainId(Trains trainsByTrainId) {
        this.trainsByTrainId = trainsByTrainId;
    }

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    public Routes getRoutesByRouteId() {
        return routesByRouteId;
    }

    public void setRoutesByRouteId(Routes routesByRouteId) {
        this.routesByRouteId = routesByRouteId;
    }

    @ManyToOne
    @JoinColumn(name = "stationid_start", referencedColumnName = "id")
    public Stations getStationsByStationidStart() {
        return stationsByStationidStart;
    }

    public void setStationsByStationidStart(Stations stationsByStationidStart) {
        this.stationsByStationidStart = stationsByStationidStart;
    }

    @ManyToOne
    @JoinColumn(name = "stationid_end", referencedColumnName = "id")
    public Stations getStationsByStationidEnd() {
        return stationsByStationidEnd;
    }

    public void setStationsByStationidEnd(Stations stationsByStationidEnd) {
        this.stationsByStationidEnd = stationsByStationidEnd;
    }
}
