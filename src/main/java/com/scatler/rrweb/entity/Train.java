/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author alexkpc
 */
@Getter
@Setter
@Entity
@Table(name = "train")
@XmlRootElement
public class Train extends AbstractEntity implements Serializable {

    //private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GenericGenerator(name="gen",strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Size(max = 45)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "seats")
    private Integer seats;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Train)) {
            return false;
        }
        Train other = (Train) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scatler.rrweb.entity.Train[ id=" + id + " ]";
    }
    
}
