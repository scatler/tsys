/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author alexkpc
 */
@Entity
@Table(name = "stations")
@NamedQueries({
    @NamedQuery(name = "Stations.findAll", query = "SELECT s FROM Stations s")})
public class Stations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;

    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }

    //**
    @JoinTable(name = "stations_routes",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id")
    )
    @ManyToMany
    private List<Routes> routes;



    public Stations() {
    }

    public Stations(Integer id) {
        this.id = id;
    }




        public Integer getId () {
        return id;
    }

        public void setId (Integer id){
        this.id = id;
    }

        public String getName () {
        return name;
    }

        public void setName (String name){
        this.name = name;
    }


        @Override
        public int hashCode () {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

        @Override
        public boolean equals (Object object){
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stations)) {
            return false;
        }
        Stations other = (Stations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

        @Override
        public String toString () {
        return "com.scatler.rrweb.Stations[ id=" + id + " ]";
    }

    }
    

