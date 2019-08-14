package com.scatler.rrweb.dto.forms;

import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.Train;
import lombok.Data;

import java.io.Serializable;

@Data
public class AvailableTrain implements Serializable {

    private Train train;
    private Integer trainRouteDay;
    private Integer route;
    private Integer station1;
    private Object dayOfStart;
    private Object arrivalToStation1Time;
    private Integer dayCountToStation1;
    private Object arrivalToStation2Time;
    private Integer dayCountToStation2;
    private Station station2;
    private Long free_tickets;

}
