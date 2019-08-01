package com.scatler.rrweb.entity;

import javax.persistence.*;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Entity
@Table(name = "trains_config", schema = "mydatabase", catalog = "")
public class TrainsConfig {
    private int id;
    private Integer trCfgType;
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
    @Column(name = "tr_cfg_type", nullable = true)
    public Integer getTrCfgType() {
        return trCfgType;
    }

    public void setTrCfgType(Integer trCfgType) {
        this.trCfgType = trCfgType;
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

        TrainsConfig that = (TrainsConfig) o;

        if (id != that.id) return false;
        if (trCfgType != null ? !trCfgType.equals(that.trCfgType) : that.trCfgType != null) return false;
        if (trainId != null ? !trainId.equals(that.trainId) : that.trainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (trCfgType != null ? trCfgType.hashCode() : 0);
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        return result;
    }

    //---------------------------------------------



}
