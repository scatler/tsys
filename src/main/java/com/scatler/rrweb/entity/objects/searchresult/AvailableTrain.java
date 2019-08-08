package com.scatler.rrweb.entity.objects.searchresult;

import com.scatler.rrweb.entity.Route;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.Train;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class AvailableTrain implements Serializable {

    //Todo change order, change object -> appropriate class
    private Train train;
    private Integer trainRouteDay;
    private Route route;
    private Station station1;
    private Object dayOfStart;
    private Object arrivalToStation1Time;
    private Integer dayCountToStation1;
    private Object arrivalToStation2Time;
    private Integer dayCountToStation2;
    private Station station2;
    private Long free_tickets;

}
