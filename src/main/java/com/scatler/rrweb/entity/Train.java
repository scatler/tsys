/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scatler.rrweb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Entity
@Table(name = "train")
public class Train extends AbstractEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @GenericGenerator(name="gen",strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "id")
    private Integer id;

    @Size(max = 45)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "seats")
    private Integer seats;

    public Train(Integer id) {
        this.id = id;
    }

}
