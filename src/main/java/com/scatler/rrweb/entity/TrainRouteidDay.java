/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "train_routeid_day")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TrainRouteidDay.findAll", query = "SELECT t FROM TrainRouteidDay t"),
        @NamedQuery(name = "TrainRouteidDay.findById", query = "SELECT t FROM TrainRouteidDay t WHERE t.id = :id"),
        @NamedQuery(name = "TrainRouteidDay.findByDayDept", query = "SELECT t FROM TrainRouteidDay t WHERE t.dayDept = :dayDept")})
public class TrainRouteidDay extends AbstractEntity implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day_dept")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dayDept;
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    @ManyToOne(optional = false)
    private RouteStation routeId;
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Train trainId;

    public TrainRouteidDay() {
    }

    public TrainRouteidDay(Integer id) {
        this.id = id;
    }

    public TrainRouteidDay(Integer id, Date dayDept) {
        this.id = id;
        this.dayDept = dayDept;
    }
}
