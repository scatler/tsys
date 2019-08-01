package com.scatler.rrweb.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Entity
public class Stations {
    private int id;
    private String name;
    private Integer stationId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "station_id", nullable = true)
    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stations stations = (Stations) o;

        if (id != stations.id) return false;
        if (name != null ? !name.equals(stations.name) : stations.name != null) return false;
        if (stationId != null ? !stationId.equals(stations.stationId) : stations.stationId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (stationId != null ? stationId.hashCode() : 0);
        return result;
    }



}
