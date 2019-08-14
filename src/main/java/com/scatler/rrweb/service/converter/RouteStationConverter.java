package com.scatler.rrweb.service.converter;

import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.entity.Route;
import com.scatler.rrweb.entity.RouteStation;
import com.scatler.rrweb.entity.Station;
import org.springframework.stereotype.Component;

@Component

public class RouteStationConverter  implements IConverter<RouteStation, RouteStationDTO> {
    @Override
    public RouteStationDTO toDto(RouteStation entity) {
        return new RouteStationDTO(entity.getId(),
                entity.getArrivalTime(),
                entity.getStopMin(),
                entity.getDay(),
                entity.getRouteId().getId(),
                entity.getStationId().getId());
    }

    @Override
    public RouteStation toEntity(RouteStationDTO dto) {
        RouteStation routeStation = new RouteStation();
        routeStation.setId(dto.getId());
        routeStation.setRouteId(new Route(dto.getRouteId()));
        routeStation.setArrivalTime(dto.getArrivalTime());
        routeStation.setStopMin(dto.getStopMin());
        routeStation.setDay(dto.getDay());
        routeStation.setStationId(new Station(dto.getStationId()));
        return routeStation;
    }
}
