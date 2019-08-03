/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexkpc
 */
@Entity
@Table(name = "trains_days_config")
@NamedQueries({
    @NamedQuery(name = "TrainsDaysConfig.findAll", query = "SELECT t FROM TrainsDaysConfig t")})
public class TrainsDaysConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day")
    @Temporal(TemporalType.DATE)
    private Date day;
    @JoinColumn(name = "tr_cfg_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TrainsConfig trCfgId;

    public TrainsDaysConfig() {
    }

    public TrainsDaysConfig(Integer id) {
        this.id = id;
    }

    public TrainsDaysConfig(Integer id, Date day) {
        this.id = id;
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public TrainsConfig getTrCfgId() {
        return trCfgId;
    }

    public void setTrCfgId(TrainsConfig trCfgId) {
        this.trCfgId = trCfgId;
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
        if (!(object instanceof TrainsDaysConfig)) {
            return false;
        }
        TrainsDaysConfig other = (TrainsDaysConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.TrainsDaysConfig[ id=" + id + " ]";
    }
    
}
