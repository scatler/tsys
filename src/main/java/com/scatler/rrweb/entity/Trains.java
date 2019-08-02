package com.scatler.rrweb.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Entity
public class Trains {
    private int id;
    private Integer locoId;
    private String name;
    private Integer seats;
    private Integer trainId;

    public void setTrainsConfigs(List<TrainsConfig> trainsConfigs) {
        this.trainsConfigs = trainsConfigs;
    }

    List<TrainsConfig> trainsConfigs = new ArrayList<>();


    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "loco_id", nullable = true)
    public Integer getLocoId() {
        return locoId;
    }

    public void setLocoId(Integer locoId) {
        this.locoId = locoId;
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
    @Column(name = "seats", nullable = true)
    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Column(name = "train_id", nullable = false)
    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    //-----------------------------------------

    @OneToMany(mappedBy="train",cascade=CascadeType.ALL)
    public List<TrainsConfig> getTrainsConfigs () {
            return trainsConfigs;
    }
}
