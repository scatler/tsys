package com.scatler.rrweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationTimeTable {

    private int trainId;
    private Date arrivalTime;
    //TODO add more
}
