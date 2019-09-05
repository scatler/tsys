/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Entity
@Table(name = "route_station")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "RouteStation.findAll", query = "SELECT r FROM RouteStation r"),
        @NamedQuery(name = "RouteStation.findById", query = "SELECT r FROM RouteStation r WHERE r.id = :id"),
        @NamedQuery(name = "RouteStation.findByArrivalTime", query = "SELECT r FROM RouteStation r WHERE r.arrivalTime = :arrivalTime"),
        @NamedQuery(name = "RouteStation.findByStopMin", query = "SELECT r FROM RouteStation r WHERE r.stopMin = :stopMin"),
        @NamedQuery(name = "RouteStation.findByDay", query = "SELECT r FROM RouteStation r WHERE r.day = :day")})
public class RouteStation extends AbstractEntity implements Serializable {

    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIME)
    private Date arrivalTime;
    @Column(name = "stop_min")
    @Temporal(TemporalType.TIME)
    private Date stopMin;
    @Column(name = "day")
    private Integer day;
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Route routeId;
    @JoinColumn(name = "station_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Station stationId;

    public RouteStation(Integer id) {
        this.id = id;
    }
}
