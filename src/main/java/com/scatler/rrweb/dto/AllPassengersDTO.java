package com.scatler.rrweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllPassengersDTO implements Serializable {
    private String name;
    private String surname;
    private Date birthday;
    private String stationFrom;
    private String stationTo;
}
