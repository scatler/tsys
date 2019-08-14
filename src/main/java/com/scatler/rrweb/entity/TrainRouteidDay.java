/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexkpc
 */
@Data
@Entity
@Table(name = "train_routeid_day")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainRouteidDay.findAll", query = "SELECT t FROM TrainRouteidDay t"),
    @NamedQuery(name = "TrainRouteidDay.findById", query = "SELECT t FROM TrainRouteidDay t WHERE t.id = :id"),
    @NamedQuery(name = "TrainRouteidDay.findByDayDept", query = "SELECT t FROM TrainRouteidDay t WHERE t.dayDept = :dayDept")})
public class TrainRouteidDay implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day_dept")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dayDept;
    //***
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "trd")
    //private List<Ticket> ticketList;
    //***
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
