package com.scatler.rrweb.service.converter;

import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.entity.Route;
import org.springframework.stereotype.Component;

@Component
public class RouteConverter implements IConverter<Route, RouteDTO> {

    @Override
    public RouteDTO toDto(Route entity) {
        return new RouteDTO(entity.getId(),entity.getName());
    }

    @Override
    public Route toEntity(RouteDTO dto) {
        Route route = new Route();
        route.setId(dto.getId());
        route.setName(dto.getName());
        return route;
    }
}
