package com.scatler.rrweb.entity.objects.searchresult;

import com.scatler.rrweb.entity.Route;
import com.scatler.rrweb.entity.Train;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class AvailableTrain {

    private Train train;
    private Integer trainRouteDay;
    private Route route;
    private Timestamp dayOfStart;
    private Time arrivalToStation1Time;
    private Integer dayCountToStation1;
    private Time arrivalToStation2Time;
    private Integer dayCountToStation2;
    private Long free_tickets;

}
