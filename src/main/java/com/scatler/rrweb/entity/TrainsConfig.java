package com.scatler.rrweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Entity
@Table(name = "trains_config", schema = "mydatabase", catalog = "")
public class TrainsConfig {
    private int id;
    private Integer trCfgType;
    private Integer trainId;



    Trains train;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tr_cfg_type", nullable = true)
    public Integer getTrCfgType() {
        return trCfgType;
    }

    public void setTrCfgType(Integer trCfgType) {
        this.trCfgType = trCfgType;
    }

/*
    @Column(name = "train_id", nullable = false )
    public Integer getTrainId() {
        return trainId;
    }
*/

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    //---------------------------------------------

    @ManyToOne
    @JoinColumn(name="train_id")
    public Trains getTrain() {
        return train;
    }
    public void setTrain(Trains train) {
        this.train = train;
    }

}
