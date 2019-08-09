package com.scatler.rrweb.entity.objects.selectors;

import org.hibernate.validator.constraints.NotEmpty;


public class TimeTableSelector {

    @NotEmpty
    private String id;
    @NotEmpty
    private String day;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
