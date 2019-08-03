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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "trains_config")

public class TrainsConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "r_cfg_type")
    private int rCfgType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rCgfType")
    private List<RoutesConfig> routesConfigList;
    @JoinColumn(name = "train_id", referencedColumnName = "train_id")
    @ManyToOne(optional = false)
    private Trains trainId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trCfgId")
    private List<TrainsDaysConfig> trainsDaysConfigList;

    public TrainsConfig() {
    }

    public TrainsConfig(Integer id) {
        this.id = id;
    }

    public TrainsConfig(Integer id, int rCfgType) {
        this.id = id;
        this.rCfgType = rCfgType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRCfgType() {
        return rCfgType;
    }

    public void setRCfgType(int rCfgType) {
        this.rCfgType = rCfgType;
    }

    public List<RoutesConfig> getRoutesConfigList() {
        return routesConfigList;
    }

    public void setRoutesConfigList(List<RoutesConfig> routesConfigList) {
        this.routesConfigList = routesConfigList;
    }

    public Trains getTrainId() {
        return trainId;
    }

    public void setTrainId(Trains trainId) {
        this.trainId = trainId;
    }

    public List<TrainsDaysConfig> getTrainsDaysConfigList() {
        return trainsDaysConfigList;
    }

    public void setTrainsDaysConfigList(List<TrainsDaysConfig> trainsDaysConfigList) {
        this.trainsDaysConfigList = trainsDaysConfigList;
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
        if (!(object instanceof TrainsConfig)) {
            return false;
        }
        TrainsConfig other = (TrainsConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.TrainsConfig[ id=" + id + " ]";
    }

}
