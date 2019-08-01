package com.scatler.rrweb.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by alexkpc on 01.08.2019.
 */
public class RoutesConfigPK implements Serializable {
    private int id;
    private int rCgfType;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "r_cgf_type", nullable = false)
    @Id
    public int getrCgfType() {
        return rCgfType;
    }

    public void setrCgfType(int rCgfType) {
        this.rCgfType = rCgfType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutesConfigPK that = (RoutesConfigPK) o;

        if (id != that.id) return false;
        if (rCgfType != that.rCgfType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + rCgfType;
        return result;
    }
}
