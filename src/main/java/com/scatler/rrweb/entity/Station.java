package com.scatler.rrweb.entity;

import javax.persistence.*;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Entity
@Table(name = "stations")
public class Station
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected int id;

    @Column(name="name")
    private String name;


}
