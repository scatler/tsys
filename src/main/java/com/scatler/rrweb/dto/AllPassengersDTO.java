package com.scatler.rrweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllPassengersDTO implements Serializable {
    private Integer Id;
    private String name;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+3")
    private Date birthday;
    private String stationFrom;
    private String stationTo;
}
