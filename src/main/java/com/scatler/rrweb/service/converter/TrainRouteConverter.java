package com.scatler.rrweb.service.converter;

import com.scatler.rrweb.dto.TrainRouteDTO;
import com.scatler.rrweb.entity.Route;
import com.scatler.rrweb.entity.RouteStation;
import com.scatler.rrweb.entity.Train;
import com.scatler.rrweb.entity.TrainRouteidDay;
import org.springframework.stereotype.Component;

@Component
public class TrainRouteConverter implements IConverter<TrainRouteidDay, TrainRouteDTO> {
    @Override
    public TrainRouteDTO toDto(TrainRouteidDay entity) {
        TrainRouteDTO dto = new TrainRouteDTO();
        dto.setTrainId(entity.getTrainId().getId());
        dto.setRouteId(entity.getRouteId().getRouteId().getId());
        dto.setDay(entity.getDayDept());
        return dto;
    }

    @Override
    public TrainRouteidDay toEntity(TrainRouteDTO dto) {
        TrainRouteidDay entity = new TrainRouteidDay();
        RouteStation rs = new RouteStation();
        rs.setRouteId(new Route(dto.getRouteId()));
        entity.setRouteId(rs);
        entity.setDayDept(dto.getDay());
        entity.setTrainId(new Train(dto.getTrainId()));
        return entity;
    }
}
