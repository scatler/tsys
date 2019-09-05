/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexkpc
 */
@Data
@Entity
@Table(name = "line")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Line.findAll", query = "SELECT l FROM Line l"),
    @NamedQuery(name = "Line.findById", query = "SELECT l FROM Line l WHERE l.id = :id"),
    @NamedQuery(name = "Line.findByName", query = "SELECT l FROM Line l WHERE l.name = :name")})
public class Line extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineId")
    private List<Station> stationList;

    public Line() {
    }

    public Line(Integer id) {
        this.id = id;
    }

    
}
