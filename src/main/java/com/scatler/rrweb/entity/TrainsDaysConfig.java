package com.scatler.rrweb.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by alexkpc on 01.08.2019.
 */
@Entity
@Table(name = "trains_days_config", schema = "mydatabase", catalog = "")
public class TrainsDaysConfig {
    private int id;
    private Date day;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "day", nullable = true)
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainsDaysConfig that = (TrainsDaysConfig) o;

        if (id != that.id) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        return result;
    }
}
