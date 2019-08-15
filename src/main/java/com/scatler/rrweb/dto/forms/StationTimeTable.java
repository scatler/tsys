package com.scatler.rrweb.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationTimeTable implements Serializable {

    private Integer trainId;
    private Integer routeId;
    private String routeName;
    private Date arrivalTime;
}
