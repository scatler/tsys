package com.scatler.rrweb.entity;

import javax.persistence.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trains trains = (Trains) o;

        if (id != trains.id) return false;
        if (locoId != null ? !locoId.equals(trains.locoId) : trains.locoId != null) return false;
        if (name != null ? !name.equals(trains.name) : trains.name != null) return false;
        if (seats != null ? !seats.equals(trains.seats) : trains.seats != null) return false;
        if (trainId != null ? !trainId.equals(trains.trainId) : trains.trainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (locoId != null ? locoId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        return result;
    }


    //-----------------------------------------


    @ManyToOne
    @JoinColumn(name="train_id")
    private TrainsConfig trainsConfig;
}
