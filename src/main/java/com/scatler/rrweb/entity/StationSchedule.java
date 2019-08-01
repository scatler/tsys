package com.scatler.rrweb.entity;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;

/**
 * Created by alexkpc on 01.08.2019.
 */

@Entity
@Immutable
public class StationSchedule {

    @Column
    private String trainNum;

    @Column
    private String arrivalTime;

}
