package com.scatler.rrweb.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "route")
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
    @NamedQuery(name = "Route.findById", query = "SELECT r FROM Route r WHERE r.id = :id"),
    @NamedQuery(name = "Route.findByName", query = "SELECT r FROM Route r WHERE r.name = :name")})
public class Route extends AbstractEntity implements Serializable {

    @Size(max = 45)
    @Column(name = "name")
    private String name;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "routeId")
    //private List<RouteStation> routeStationList;

    public Route() {
    }

    public Route(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Route{}" + id;
    }
}
